# Día 10: Factory

En el décimo día del Advent of Code 2025, el problema se desarrolla en una fábrica del Polo Norte. Las máquinas están apagadas y los elfos no conocen el procedimiento de inicialización, porque parte del manual fue destruida.

El manual contiene una línea por cada máquina. Cada línea describe tres elementos:

* Un diagrama de luces indicadoras entre corchetes.
* Una lista de botones entre paréntesis.
* Un conjunto de requisitos de joltage entre llaves.

Ejemplo:

```text
[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
```

En la parte 1, las luces deben configurarse según el diagrama. En la parte 2, las luces se ignoran y se configuran contadores de joltage.

---

## Parte 1

En la primera parte, cada máquina tiene varias luces indicadoras. Todas empiezan apagadas.

El diagrama indica el estado objetivo:

* `.` significa luz apagada.
* `#` significa luz encendida.

Por ejemplo:

```text
[.##.]
```

indica que la máquina tiene cuatro luces y que el objetivo es:

```text
apagada, encendida, encendida, apagada
```

Cada botón alterna el estado de ciertas luces. Si una luz está apagada, se enciende; si está encendida, se apaga.

Por ejemplo, un botón:

```text
(0,2)
```

alterna la primera y la tercera luz.

El objetivo es calcular el menor número total de pulsaciones necesario para configurar correctamente todas las máquinas.

En el ejemplo oficial, las pulsaciones mínimas son:

```text
2 + 3 + 2 = 7
```


---

## Parte 2

En la segunda parte, las luces dejan de ser relevantes. Ahora se usan los mismos botones para incrementar contadores de joltage.

Cada máquina tiene varios contadores, todos inicialmente a `0`. Los requisitos de joltage indican el valor final que debe tener cada contador.

Por ejemplo:

```text
{3,5,4,7}
```

significa que hay cuatro contadores y que deben terminar con los valores:

```text
3, 5, 4, 7
```

Cada botón incrementa en `1` los contadores indicados por su cableado.

Por ejemplo:

```text
(1,3)
```

incrementa el segundo y el cuarto contador.

El objetivo es calcular el menor número total de pulsaciones necesario para configurar correctamente todos los contadores de joltage de todas las máquinas.

En el ejemplo oficial, las pulsaciones mínimas son:

```text
10 + 12 + 11 = 33
```


---

# Estructura del proyecto

La solución del Día 10 mantiene la misma estructura modular usada en los días anteriores:

```text
day10
├── Day10Main.java
├── common
│   ├── ButtonWiring.java
│   ├── IndicatorLightDiagram.java
│   ├── Machine.java
│   ├── MachineManual.java
│   └── MachineManualParser.java
├── part1
│   ├── Day10Part1Solver.java
│   └── MinimumButtonPressCalculator.java
└── part2
    ├── Day10Part2Solver.java
    └── MinimumJoltagePressCalculator.java
```

La idea principal es separar:

* El punto de entrada del día.
* Las clases comunes del dominio.
* El parseo del manual.
* La lógica específica de la parte 1.
* La lógica específica de la parte 2.
* El cálculo mínimo de pulsaciones para luces.
* El cálculo mínimo de pulsaciones para joltage.

---

# Clases del paquete `day10.common`

El paquete `day10.common` contiene las clases compartidas por ambas partes.

---

## `ButtonWiring`

El record `ButtonWiring` representa el cableado de un botón.

Internamente almacena una máscara de bits:

```java
public record ButtonWiring(int toggledLightsMask)
```

En la parte 1, esa máscara indica qué luces se alternan.

En la parte 2, la misma máscara indica qué contadores de joltage se incrementan.

Su método principal para la parte 2 es:

```java
public boolean affects(int counterIndex)
```

Este método indica si el botón afecta a un contador concreto.

---

## `IndicatorLightDiagram`

El record `IndicatorLightDiagram` representa el patrón objetivo de luces de una máquina.

Está formado por:

```java
public record IndicatorLightDiagram(String pattern, int targetMask)
```

* `pattern`: texto original del diagrama, formado por `.` y `#`.
* `targetMask`: representación binaria del patrón objetivo.

El método estático:

```java
public static IndicatorLightDiagram from(String pattern)
```

convierte un patrón textual en una máscara de bits.

Por ejemplo:

```text
.##.
```

se transforma en una máscara donde están activos los bits de las luces que deben estar encendidas.

---

## `Machine`

El record `Machine` representa una máquina del manual.

Está formado por:

```java
public record Machine(
        IndicatorLightDiagram diagram,
        List<ButtonWiring> buttons,
        List<Integer> joltageRequirements
)
```

Contiene:

* El diagrama de luces.
* La lista de botones.
* Los requisitos de joltage.

Esta clase valida que el diagrama, los botones y los requisitos no sean nulos, y que exista al menos un botón.

También copia las listas usando `List.copyOf`.

Sus métodos principales son:

```java
public int lightCount()
```

Devuelve el número de luces.

```java
public int counterCount()
```

Devuelve el número de contadores de joltage.

---

## `MachineManual`

El record `MachineManual` representa el manual completo de máquinas.

Internamente almacena una lista de máquinas:

```java
public record MachineManual(List<Machine> machines)
```

Valida que la lista no sea nula y que contenga al menos una máquina.

También copia la lista con `List.copyOf` para evitar modificaciones externas.

---

## `MachineManualParser`

La clase `MachineManualParser` transforma las líneas del input en un `MachineManual`.

Cada línea contiene:

* Un diagrama entre corchetes.
* Uno o más botones entre paréntesis.
* Requisitos de joltage entre llaves.

El parser usa expresiones regulares para extraer cada parte.

Sus responsabilidades principales son:

1. Recorrer las líneas del input.
2. Ignorar líneas en blanco.
3. Extraer el diagrama de luces.
4. Extraer los botones.
5. Extraer los requisitos de joltage.
6. Crear objetos `Machine`.

---

# Clases del paquete `day10.part1`

El paquete `day10.part1` contiene la solución específica de la primera parte.

---

## `Day10Part1Solver`

La clase `Day10Part1Solver` resuelve la primera parte del Día 10.

Implementa la interfaz común `PuzzleSolver`.

Su método `solve` realiza estos pasos:

1. Usa `MachineManualParser` para convertir el input en un `MachineManual`.
2. Recorre todas las máquinas del manual.
3. Usa `MinimumButtonPressCalculator` para calcular las pulsaciones mínimas de cada máquina.
4. Suma todos los resultados.
5. Devuelve el total.

---

## `MinimumButtonPressCalculator`

La clase `MinimumButtonPressCalculator` calcula el mínimo número de pulsaciones necesarias para configurar las luces de una máquina.

Como pulsar dos veces el mismo botón en la parte 1 cancela su efecto, cada botón solo necesita considerarse como pulsado o no pulsado.

La clase representa los estados de luces mediante máscaras binarias.

El algoritmo funciona así:

1. Calcula el número total de estados posibles de luces.
2. Crea un array con las pulsaciones mínimas para alcanzar cada estado.
3. Empieza en el estado `0`, donde todas las luces están apagadas.
4. Para cada botón, calcula qué nuevo estado se obtiene aplicando XOR con su máscara.
5. Actualiza el mínimo de pulsaciones.
6. Al final, consulta el coste mínimo para llegar al estado objetivo.

---

# Clases del paquete `day10.part2`

El paquete `day10.part2` contiene la solución específica de la segunda parte.

---

## `Day10Part2Solver`

La clase `Day10Part2Solver` resuelve la segunda parte del Día 10.

También implementa la interfaz `PuzzleSolver`.

Su método `solve` realiza estos pasos:

1. Usa `MachineManualParser` para convertir el input en un `MachineManual`.
2. Recorre todas las máquinas del manual.
3. Usa `MinimumJoltagePressCalculator` para calcular las pulsaciones mínimas de cada máquina.
4. Suma todos los resultados.
5. Devuelve el total.

---

## `MinimumJoltagePressCalculator`

La clase `MinimumJoltagePressCalculator` calcula el mínimo número de pulsaciones necesarias para alcanzar los requisitos de joltage.

En esta parte, pulsar un botón suma `1` a cada contador que afecta. Por tanto, el problema se puede modelar como un sistema de ecuaciones lineales.

Cada variable representa cuántas veces se pulsa un botón.

Cada ecuación representa el valor final que debe alcanzar un contador.

El proceso general es:

1. Construir una matriz a partir de los botones y requisitos.
2. Reducir el sistema mediante eliminación gaussiana.
3. Identificar columnas pivote y columnas libres.
4. Buscar soluciones enteras no negativas.
5. Calcular el total de pulsaciones de cada solución válida.
6. Devolver la solución con menor número total de pulsaciones.

Esta clase también contiene estructuras internas para representar el sistema lineal reducido y buscar soluciones enteras.

---

# Clase del paquete `day10`

El paquete `day10` contiene la clase principal del Día 10.

---

## `Day10Main`

La clase `Day10Main` es el punto de entrada para ejecutar la solución completa del Día 10.

El método `main` realiza los siguientes pasos:

1. Lee todas las líneas del archivo `src/main/resources/day10/input.txt`.
2. Crea una instancia de `Day10Part1Solver`.
3. Crea una instancia de `Day10Part2Solver`.
4. Ejecuta el método `solve` de ambos solvers.
5. Guarda los resultados.
6. Imprime los resultados por consola.

Esta clase utiliza la interfaz `PuzzleSolver` para referenciar ambos solvers:

```java
PuzzleSolver part1Solver = new Day10Part1Solver();
PuzzleSolver part2Solver = new Day10Part2Solver();
```

---

# Interfaz común del proyecto

El proyecto utiliza la interfaz común `PuzzleSolver`, ubicada en el paquete `aoc2025.common`.

Esta interfaz define el contrato común para todos los solvers del Advent of Code:

```java
long solve(List<String> lines);
```

En el Día 10, tanto `Day10Part1Solver` como `Day10Part2Solver` implementan esta interfaz.

---

# Fundamentos de diseño utilizados

En la solución del Día 10 se utilizan los siguientes fundamentos de diseño:

* Alta cohesión.
* Bajo acoplamiento.
* Modularidad.
* Código expresivo.
* Abstracción.

---

# Principios de diseño aplicados

En la solución del Día 10 se aplican los siguientes principios de diseño:

* Principio de Responsabilidad Única, SRP.
* Principio Abierto/Cerrado, OCP.
* Principio de Sustitución de Liskov, LSP.
* Principio de Segregación de Interfaces, ISP.
* Principio de Inversión de Dependencias, DIP.
* Principio DRY.
* Ley de Demeter.
* Principio YAGNI.

---

# Patrones de diseño aplicados

En la solución del Día 10 se utilizan los siguientes patrones de diseño:

* Iterator.
* Factory Method, aplicado mediante `IndicatorLightDiagram.from`.

---

# Patrones no aplicados

En la solución del Día 10 no se aplican los siguientes patrones de diseño:

* Singleton.
* Adapter.
* Decorator.
* Observer.

---
