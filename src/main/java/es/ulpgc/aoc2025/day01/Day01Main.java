package es.ulpgc.aoc2025.day01;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day01.part1.Day01Part1Solver;
import es.ulpgc.aoc2025.day01.part2.Day01Part2Solver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day01Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Path.of("src/main/resources/day01/input.txt")
        );

        PuzzleSolver part1Solver = new Day01Part1Solver();
        PuzzleSolver part2Solver = new Day01Part2Solver();

        long part1Result = part1Solver.solve(lines);
        long part2Result = part2Solver.solve(lines);

        System.out.println("Day 01 - Part 1: " + part1Result);
        System.out.println("Day 01 - Part 2: " + part2Result);
    }
}