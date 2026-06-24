package es.ulpgc.aoc2025.day08.common;

import java.util.List;

public record JunctionBoxMap(List<JunctionBoxPosition> positions) {

    public JunctionBoxMap {
        if (positions == null) {
            throw new IllegalArgumentException("Positions cannot be null");
        }

        if (positions.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one junction box");
        }

        positions = List.copyOf(positions);
    }

    public int size() {
        return positions.size();
    }

    public JunctionBoxPosition positionAt(int index) {
        return positions.get(index);
    }
}