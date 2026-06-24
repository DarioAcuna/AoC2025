package es.ulpgc.aoc2025.day07.part2;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day07.common.TachyonManifold;
import es.ulpgc.aoc2025.day07.common.TachyonManifoldParser;

import java.util.List;

public class Day07Part2Solver implements PuzzleSolver {

    private final TachyonManifoldParser parser = new TachyonManifoldParser();
    private final QuantumTimelineCounter timelineCounter = new QuantumTimelineCounter();

    @Override
    public long solve(List<String> lines) {
        TachyonManifold manifold = parser.parse(lines);

        return timelineCounter.countTimelinesIn(manifold);
    }
}