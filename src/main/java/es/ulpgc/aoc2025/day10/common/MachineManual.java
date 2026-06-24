package es.ulpgc.aoc2025.day10.common;

import java.util.List;

public record MachineManual(List<Machine> machines) {

    public MachineManual {
        if (machines == null) {
            throw new IllegalArgumentException("Machines cannot be null");
        }

        if (machines.isEmpty()) {
            throw new IllegalArgumentException("Manual must contain at least one machine");
        }

        machines = List.copyOf(machines);
    }
}