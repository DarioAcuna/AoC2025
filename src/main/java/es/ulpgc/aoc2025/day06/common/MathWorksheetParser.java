package es.ulpgc.aoc2025.day06.common;

import java.util.ArrayList;
import java.util.List;

public class MathWorksheetParser {

    public MathWorksheet parse(List<String> lines) {
        List<String> rows = new ArrayList<>();

        for (String line : lines) {
            if (!line.isBlank()) {
                rows.add(line);
            }
        }

        // Añadido para Day 06.
        // Al copiar el input, algunas filas pueden perder espacios finales.
        // Como el problema depende de columnas, rellenamos todas las filas
        // hasta la anchura máxima usando espacios a la derecha.
        return new MathWorksheet(normalizeWidth(rows));
    }

    private List<String> normalizeWidth(List<String> rows) {
        int width = maxWidthOf(rows);
        List<String> normalizedRows = new ArrayList<>();

        for (String row : rows) {
            normalizedRows.add(padRight(row, width));
        }

        return normalizedRows;
    }

    private int maxWidthOf(List<String> rows) {
        int maxWidth = 0;

        for (String row : rows) {
            if (row.length() > maxWidth) {
                maxWidth = row.length();
            }
        }

        return maxWidth;
    }

    private String padRight(String text, int width) {
        return text + " ".repeat(width - text.length());
    }
}