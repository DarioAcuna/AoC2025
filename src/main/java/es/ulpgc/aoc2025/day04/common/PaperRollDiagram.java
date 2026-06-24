package es.ulpgc.aoc2025.day04.common;

import java.util.List;

public record PaperRollDiagram(List<String> rows) {

    public PaperRollDiagram {
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

            if (!row.matches("[.@]+")) {
                throw new IllegalArgumentException("Rows can only contain '.' and '@'");
            }
        }

        rows = List.copyOf(rows);
    }

    public int height() {
        return rows.size();
    }

    public int width() {
        return rows.getFirst().length();
    }

    public boolean hasPaperRollAt(int row, int column) {
        return rows.get(row).charAt(column) == '@';
    }

    public boolean contains(int row, int column) {
        return 0 <= row && row < height()
                && 0 <= column && column < width();
    }
}