package es.ulpgc.aoc2025.day03.part1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Part1SolverTest {

    @Test
    void shouldSolveOfficialExample() {
        List<String> input = List.of(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
        );

        Day03Part1Solver solver = new Day03Part1Solver();

        assertEquals(357, solver.solve(input));
    }
}