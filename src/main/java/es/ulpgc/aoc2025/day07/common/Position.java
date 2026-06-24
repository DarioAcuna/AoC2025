package es.ulpgc.aoc2025.day07.common;

public record Position(int row, int column) {

    public Position below() {
        return new Position(row + 1, column);
    }

    public Position left() {
        return new Position(row, column - 1);
    }

    public Position right() {
        return new Position(row, column + 1);
    }
}