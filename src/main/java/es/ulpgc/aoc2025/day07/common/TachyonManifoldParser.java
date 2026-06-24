package es.ulpgc.aoc2025.day07.common;

import java.util.ArrayList;
import java.util.List;

public class TachyonManifoldParser {

    public TachyonManifold parse(List<String> lines) {
        List<String> rows = new ArrayList<>();

        for (String line : lines) {
            if (!line.isBlank()) {
                rows.add(line.trim());
            }
        }

        return new TachyonManifold(rows);
    }
}