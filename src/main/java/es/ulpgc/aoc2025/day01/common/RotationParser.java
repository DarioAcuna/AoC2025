package es.ulpgc.aoc2025.day01.common;

public class RotationParser {

    public Rotation parse(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("Rotation line cannot be empty");
        }

        char directionChar = line.charAt(0);
        int distance = Integer.parseInt(line.substring(1));

        Direction direction = switch (directionChar) {
            case 'L' -> Direction.LEFT;
            case 'R' -> Direction.RIGHT;
            default -> throw new IllegalArgumentException("Invalid direction: " + directionChar);
        };

        return new Rotation(direction, distance);
    }
}
