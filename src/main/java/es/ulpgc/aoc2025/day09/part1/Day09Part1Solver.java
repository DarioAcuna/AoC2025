package es.ulpgc.aoc2025.day09.part1;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day09.common.RedTileMap;
import es.ulpgc.aoc2025.day09.common.RedTileMapParser;

import java.util.List;

public class Day09Part1Solver implements PuzzleSolver {

    private final RedTileMapParser parser = new RedTileMapParser();
    private final LargestRedCornerRectangleAnalyzer analyzer = new LargestRedCornerRectangleAnalyzer();

    @Override
    public long solve(List<String> lines) {
        RedTileMap map = parser.parse(lines);

        return analyzer.largestRectangleAreaIn(map);
    }
}