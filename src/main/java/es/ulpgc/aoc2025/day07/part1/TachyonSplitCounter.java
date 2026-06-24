package es.ulpgc.aoc2025.day07.part1;

import es.ulpgc.aoc2025.day07.common.Position;
import es.ulpgc.aoc2025.day07.common.TachyonManifold;

import java.util.HashSet;
import java.util.Set;

public class TachyonSplitCounter {

    public long countSplitsIn(TachyonManifold manifold) {
        Position start = manifold.startPosition();

        Set<Integer> activeColumns = new HashSet<>();
        activeColumns.add(start.column());

        long splits = 0;

        for (int row = start.row() + 1; row < manifold.height(); row++) {
            Set<Integer> nextActiveColumns = new HashSet<>();

            for (int column : activeColumns) {
                Position position = new Position(row, column);

                if (!manifold.contains(position)) {
                    continue;
                }

                if (manifold.hasSplitterAt(position)) {
                    splits++;
                    nextActiveColumns.add(column - 1);
                    nextActiveColumns.add(column + 1);
                } else {
                    nextActiveColumns.add(column);
                }
            }

            activeColumns = nextActiveColumns;
        }

        return splits;
    }
}