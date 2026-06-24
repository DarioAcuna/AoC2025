package es.ulpgc.aoc2025.day09;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day09.part1.Day09Part1Solver;
import es.ulpgc.aoc2025.day09.part2.Day09Part2Solver; // añadido para parte 2

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day09Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Path.of("src/main/resources/day09/input.txt")
        );

        PuzzleSolver part1Solver = new Day09Part1Solver();
        PuzzleSolver part2Solver = new Day09Part2Solver(); // añadido para parte 2

        long part1Result = part1Solver.solve(lines);
        long part2Result = part2Solver.solve(lines); // añadido para parte 2

        System.out.println("Day 09 - Part 1: " + part1Result);
        System.out.println("Day 09 - Part 2: " + part2Result); // añadido para parte 2
    }
}