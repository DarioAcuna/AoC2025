package es.ulpgc.aoc2025.day07.part1;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day07.common.TachyonManifold;
import es.ulpgc.aoc2025.day07.common.TachyonManifoldParser;

import java.util.List;

public class Day07Part1Solver implements PuzzleSolver {

    private final TachyonManifoldParser parser = new TachyonManifoldParser();
    private final TachyonSplitCounter splitCounter = new TachyonSplitCounter();

    @Override
    public long solve(List<String> lines) {
        TachyonManifold manifold = parser.parse(lines);

        return splitCounter.countSplitsIn(manifold);
    }
}