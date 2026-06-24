package es.ulpgc.aoc2025.day04.common;

import java.util.ArrayList;
import java.util.List;

public class PaperRollDiagramParser {

    public PaperRollDiagram parse(List<String> lines) {
        List<String> rows = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            rows.add(line.trim());
        }

        return new PaperRollDiagram(rows);
    }
}