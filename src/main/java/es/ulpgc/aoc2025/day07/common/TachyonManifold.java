package es.ulpgc.aoc2025.day07.common;

import java.util.List;

public record TachyonManifold(List<String> rows) {

    private static final char START = 'S';
    private static final char SPLITTER = '^';
    private static final char EMPTY = '.';

    public TachyonManifold {
        if (rows == null) {
            throw new IllegalArgumentException("Rows cannot be null");
        }

        if (rows.isEmpty()) {
            throw new IllegalArgumentException("Rows cannot be empty");
        }

        int width = rows.getFirst().length();

        for (String row : rows) {
            if (row == null) {
                throw new IllegalArgumentException("Row cannot be null");
            }

            if (row.length() != width) {
                throw new IllegalArgumentException("All rows must have the same width");
            }

            if (!row.matches("[.S^]+")) {
                throw new IllegalArgumentException("Rows can only contain '.', 'S' and '^'");
            }
        }

        if (countStartPositionsIn(rows) != 1) {
            throw new IllegalArgumentException("The manifold must contain exactly one start position");
        }

        rows = List.copyOf(rows);
    }

    public int height() {
        return rows.size();
    }

    public int width() {
        return rows.getFirst().length();
    }

    public Position startPosition() {
        for (int row = 0; row < height(); row++) {
            int column = rows.get(row).indexOf(START);

            if (column != -1) {
                return new Position(row, column);
            }
        }

        throw new IllegalStateException("Start position not found");
    }

    public boolean hasSplitterAt(Position position) {
        return contains(position)
                && rows.get(position.row()).charAt(position.column()) == SPLITTER;
    }

    public boolean contains(Position position) {
        return 0 <= position.row() && position.row() < height()
                && 0 <= position.column() && position.column() < width();
    }

    private static int countStartPositionsIn(List<String> rows) {
        int count = 0;

        for (String row : rows) {
            for (int column = 0; column < row.length(); column++) {
                if (row.charAt(column) == START) {
                    count++;
                }
            }
        }

        return count;
    }
}