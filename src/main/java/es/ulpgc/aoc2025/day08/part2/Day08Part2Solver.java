package es.ulpgc.aoc2025.day08.part2;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day08.common.JunctionBoxMap;
import es.ulpgc.aoc2025.day08.common.JunctionBoxMapParser;

import java.util.List;

public class Day08Part2Solver implements PuzzleSolver {

    private final JunctionBoxMapParser parser = new JunctionBoxMapParser();
    private final FinalCircuitConnectionAnalyzer analyzer = new FinalCircuitConnectionAnalyzer();

    @Override
    public long solve(List<String> lines) {
        JunctionBoxMap map = parser.parse(lines);

        return analyzer.productOfXCoordinatesOfFinalConnection(map);
    }
}