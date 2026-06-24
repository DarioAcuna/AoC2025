package es.ulpgc.aoc2025.day08.part1;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day08.common.JunctionBoxMap;
import es.ulpgc.aoc2025.day08.common.JunctionBoxMapParser;

import java.util.List;

public class Day08Part1Solver implements PuzzleSolver {

    private static final int CONNECTIONS_TO_MAKE = 1000;

    private final JunctionBoxMapParser parser = new JunctionBoxMapParser();
    private final ShortestConnectionCircuitAnalyzer analyzer = new ShortestConnectionCircuitAnalyzer();

    private final int connectionsToMake;

    public Day08Part1Solver() {
        this(CONNECTIONS_TO_MAKE);
    }

    public Day08Part1Solver(int connectionsToMake) {
        if (connectionsToMake < 0) {
            throw new IllegalArgumentException("Connections to make cannot be negative");
        }

        this.connectionsToMake = connectionsToMake;
    }

    @Override
    public long solve(List<String> lines) {
        JunctionBoxMap map = parser.parse(lines);

        return analyzer.productOfThreeLargestCircuitSizesAfter(
                map,
                connectionsToMake
        );
    }
}