package es.ulpgc.aoc2025.day10.common;

public record ButtonWiring(int toggledLightsMask) {

    public ButtonWiring {
        if (toggledLightsMask < 0) {
            throw new IllegalArgumentException("Toggled lights mask cannot be negative");
        }
    }

    // Añadido para parte 2.
    // En modo joltage, la misma máscara indica qué contadores incrementa el botón.
    public boolean affects(int counterIndex) {
        return (toggledLightsMask & (1 << counterIndex)) != 0;
    }
}