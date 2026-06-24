package es.ulpgc.aoc2025.day06.common;

import java.util.List;

public record MathWorksheet(List<String> rows) {

    public MathWorksheet {
        if (rows == null) {
            throw new IllegalArgumentException("Rows cannot be null");
        }

        if (rows.size() < 2) {
            throw new IllegalArgumentException("A worksheet must contain number rows and an operation row");
        }

        int width = rows.get(0).length();

        for (String row : rows) {
            if (row == null) {
                throw new IllegalArgumentException("Row cannot be null");
            }

            if (row.length() != width) {
                throw new IllegalArgumentException("All rows must have the same width");
            }
        }

        rows = List.copyOf(rows);
    }

    public int height() {
        return rows.size();
    }

    public int width() {
        return rows.get(0).length();
    }

    public int operationRowIndex() {
        return height() - 1;
    }

    public boolean isBlankColumn(int column) {
        for (String row : rows) {
            if (row.charAt(column) != ' ') {
                return false;
            }
        }

        return true;
    }

    public String textAt(int row, int startColumn, int endColumn) {
        return rows.get(row).substring(startColumn, endColumn);
    }

    // Añadido para la parte 2.
    // Permite leer un carácter concreto de la hoja.
    public char characterAt(int row, int column) {
        return rows.get(row).charAt(column);
    }
}