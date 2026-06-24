package es.ulpgc.aoc2025.day10.common;

import java.util.List;

public record Machine(
        IndicatorLightDiagram diagram,
        List<ButtonWiring> buttons,
        List<Integer> joltageRequirements
) {

    public Machine {
        if (diagram == null) {
            throw new IllegalArgumentException("Diagram cannot be null");
        }

        if (buttons == null) {
            throw new IllegalArgumentException("Buttons cannot be null");
        }

        if (buttons.isEmpty()) {
            throw new IllegalArgumentException("A machine must contain at least one button");
        }

        if (joltageRequirements == null) {
            throw new IllegalArgumentException("Joltage requirements cannot be null");
        }

        buttons = List.copyOf(buttons);
        joltageRequirements = List.copyOf(joltageRequirements);
    }

    public int lightCount() {
        return diagram.lightCount();
    }

    // Añadido para parte 2.
    // En modo joltage, cada requisito representa un contador.
    public int counterCount() {
        return joltageRequirements.size();
    }
}