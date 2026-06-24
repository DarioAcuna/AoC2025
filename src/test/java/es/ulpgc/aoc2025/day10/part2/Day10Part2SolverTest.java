package es.ulpgc.aoc2025.day10.part2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Part2SolverTest {

    @Test
    void shouldSolveOfficialExample() {
        List<String> input = List.of(
                "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}",
                "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}",
                "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}"
        );

        Day10Part2Solver solver = new Day10Part2Solver();

        assertEquals(33, solver.solve(input));
    }
}