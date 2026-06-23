package es.ulpgc.aoc2025.day01.common;

public record Rotation(Direction direction, int distance) {

    public Rotation {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }

    }
}
