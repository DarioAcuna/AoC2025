package es.ulpgc.aoc2025.day01.part2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Part2SolverTest {

    @Test
    void shouldSolveExample() {
        List<String> input = List.of(
                "L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"
        );

        Day01Part2Solver solver = new Day01Part2Solver();

        assertEquals(6, solver.solve(input));
    }

    @Test
    void shouldCountSeveralZeroClicksInOneLongRotation() {
        List<String> input = List.of("R1000");

        Day01Part2Solver solver = new Day01Part2Solver();

        assertEquals(10, solver.solve(input));
    }
}
