package es.ulpgc.aoc2025.day10.part1;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day10.common.Machine;
import es.ulpgc.aoc2025.day10.common.MachineManual;
import es.ulpgc.aoc2025.day10.common.MachineManualParser;

import java.util.List;

public class Day10Part1Solver implements PuzzleSolver {

    private final MachineManualParser parser = new MachineManualParser();
    private final MinimumButtonPressCalculator calculator = new MinimumButtonPressCalculator();

    @Override
    public long solve(List<String> lines) {
        MachineManual manual = parser.parse(lines);
        long totalPresses = 0;

        for (Machine machine : manual.machines()) {
            totalPresses += calculator.minimumPressesFor(machine);
        }

        return totalPresses;
    }
}