package es.ulpgc.aoc2025.day06;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day06.part1.Day06Part1Solver;
import es.ulpgc.aoc2025.day06.part2.Day06Part2Solver; // añadido para parte 2

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day06Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Path.of("src/main/resources/day06/input.txt")
        );

        PuzzleSolver part1Solver = new Day06Part1Solver();
        PuzzleSolver part2Solver = new Day06Part2Solver(); // añadido para parte 2

        long part1Result = part1Solver.solve(lines);
        long part2Result = part2Solver.solve(lines); // añadido para parte 2

        System.out.println("Day 06 - Part 1: " + part1Result);
        System.out.println("Day 06 - Part 2: " + part2Result); // añadido para parte 2
    }
}