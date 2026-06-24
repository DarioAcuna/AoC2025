package es.ulpgc.aoc2025.day07.part2;

import es.ulpgc.aoc2025.day07.common.Position;
import es.ulpgc.aoc2025.day07.common.TachyonManifold;

import java.util.HashMap;
import java.util.Map;

public class QuantumTimelineCounter {

    public long countTimelinesIn(TachyonManifold manifold) {
        Position start = manifold.startPosition();

        Map<Integer, Long> activeTimelinesByColumn = new HashMap<>();
        activeTimelinesByColumn.put(start.column(), 1L);

        for (int row = start.row() + 1; row < manifold.height(); row++) {
            Map<Integer, Long> nextTimelinesByColumn = new HashMap<>();

            for (Map.Entry<Integer, Long> entry : activeTimelinesByColumn.entrySet()) {
                int column = entry.getKey();
                long timelines = entry.getValue();

                Position position = new Position(row, column);

                if (!manifold.contains(position)) {
                    continue;
                }

                if (manifold.hasSplitterAt(position)) {
                    addTimelinesTo(nextTimelinesByColumn, column - 1, timelines, manifold);
                    addTimelinesTo(nextTimelinesByColumn, column + 1, timelines, manifold);
                } else {
                    addTimelinesTo(nextTimelinesByColumn, column, timelines, manifold);
                }
            }

            activeTimelinesByColumn = nextTimelinesByColumn;
        }

        return totalTimelinesIn(activeTimelinesByColumn);
    }

    private void addTimelinesTo(
            Map<Integer, Long> timelinesByColumn,
            int column,
            long timelines,
            TachyonManifold manifold
    ) {
        if (column < 0 || column >= manifold.width()) {
            return;
        }

        timelinesByColumn.merge(column, timelines, Long::sum);
    }

    private long totalTimelinesIn(Map<Integer, Long> timelinesByColumn) {
        long total = 0;

        for (long timelines : timelinesByColumn.values()) {
            total += timelines;
        }

        return total;
    }
}