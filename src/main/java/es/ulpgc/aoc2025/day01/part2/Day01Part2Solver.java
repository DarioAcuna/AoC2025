package es.ulpgc.aoc2025.day01.part2;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day01.common.CircularDial;
import es.ulpgc.aoc2025.day01.common.Rotation;
import es.ulpgc.aoc2025.day01.common.RotationParser;

import java.util.List;

public class Day01Part2Solver implements PuzzleSolver {

    private static final int INITIAL_POSITION = 50;

    private final RotationParser parser = new RotationParser();

    @Override
    public long solve(List<String> lines) {
        CircularDial dial = new CircularDial(INITIAL_POSITION);
        long password = 0;

        for (String line : lines) {
            Rotation rotation = parser.parse(line);
            password += dial.countZeroClicksDuring(rotation);
        }

        return password;
    }
}