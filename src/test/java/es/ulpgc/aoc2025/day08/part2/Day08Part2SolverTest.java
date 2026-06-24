package es.ulpgc.aoc2025.day08.part2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Part2SolverTest {

    @Test
    void shouldSolveOfficialExample() {
        List<String> input = List.of(
                "162,817,812",
                "57,618,57",
                "906,360,560",
                "592,479,940",
                "352,342,300",
                "466,668,158",
                "542,29,236",
                "431,825,988",
                "739,650,466",
                "52,470,668",
                "216,146,977",
                "819,987,18",
                "117,168,530",
                "805,96,715",
                "346,949,466",
                "970,615,88",
                "941,993,340",
                "862,61,35",
                "984,92,344",
                "425,690,689"
        );

        Day08Part2Solver solver = new Day08Part2Solver();

        assertEquals(25272, solver.solve(input));
    }
}