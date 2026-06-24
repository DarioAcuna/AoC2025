package es.ulpgc.aoc2025.day09.common;

public record RedTilePosition(int x, int y) {

    public long rectangleAreaWith(RedTilePosition other) {
        long width = Math.abs((long) this.x - other.x()) + 1;
        long height = Math.abs((long) this.y - other.y()) + 1;

        return width * height;
    }
}