package es.ulpgc.aoc2025.day06.part1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Part1SolverTest {

    @Test
    void shouldSolveOfficialExample() {
        List<String> input = List.of(
                "123 328  51 64 ",
                " 45 64  387 23 ",
                "  6 98  215 314",
                "*   +   *   +  "
        );

        Day06Part1Solver solver = new Day06Part1Solver();

        assertEquals(4277556, solver.solve(input));
    }
}