package es.ulpgc.aoc2025.day06.part2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Part2SolverTest {

    @Test
    void shouldSolveOfficialExample() {
        List<String> input = List.of(
                "123 328  51 64 ",
                " 45 64  387 23 ",
                "  6 98  215 314",
                "*   +   *   +  "
        );

        Day06Part2Solver solver = new Day06Part2Solver();

        assertEquals(3263827, solver.solve(input));
    }
}