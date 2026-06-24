package es.ulpgc.aoc2025.day03;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day03.part1.Day03Part1Solver;
import es.ulpgc.aoc2025.day03.part2.Day03Part2Solver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day03Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Path.of("src/main/resources/day03/input.txt")
        );

        PuzzleSolver part1Solver = new Day03Part1Solver();
        PuzzleSolver part2Solver = new Day03Part2Solver();

        long part1Result = part1Solver.solve(lines);
        long part2Result = part2Solver.solve(lines);

        System.out.println("Day 03 - Part 1: " + part1Result);
        System.out.println("Day 03 - Part 2: " + part2Result);
    }
}