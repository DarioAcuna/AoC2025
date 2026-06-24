package es.ulpgc.aoc2025.day03.part1;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day03.common.BatteryBank;
import es.ulpgc.aoc2025.day03.common.BatteryBankParser;
import es.ulpgc.aoc2025.day03.common.MaximumJoltageCalculator;

import java.util.List;

public class Day03Part1Solver implements PuzzleSolver {

    private static final int BATTERIES_TO_TURN_ON = 2;

    private final BatteryBankParser parser = new BatteryBankParser();
    private final MaximumJoltageCalculator calculator = new MaximumJoltageCalculator();

    @Override
    public long solve(List<String> lines) {
        List<BatteryBank> banks = parser.parse(lines);
        long totalJoltage = 0;

        for (BatteryBank bank : banks) {
            totalJoltage += calculator.calculate(bank, BATTERIES_TO_TURN_ON);
        }

        return totalJoltage;
    }
}