package es.ulpgc.aoc2025.day08.common;

import java.util.ArrayList;
import java.util.List;

public class JunctionBoxMapParser {

    public JunctionBoxMap parse(List<String> lines) {
        List<JunctionBoxPosition> positions = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            positions.add(parsePosition(line.trim()));
        }

        return new JunctionBoxMap(positions);
    }

    private JunctionBoxPosition parsePosition(String line) {
        String[] coordinates = line.split(",");

        if (coordinates.length != 3) {
            throw new IllegalArgumentException("Invalid position: " + line);
        }

        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        int z = Integer.parseInt(coordinates[2]);

        return new JunctionBoxPosition(x, y, z);
    }
}