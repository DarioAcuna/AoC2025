package es.ulpgc.aoc2025.day09.part2;

import es.ulpgc.aoc2025.day09.common.RedTilePosition;

import java.util.List;

public record TileRectangle(int minX, int maxX, int minY, int maxY) {

    public TileRectangle {
        if (minX > maxX) {
            throw new IllegalArgumentException("Min x cannot be greater than max x");
        }

        if (minY > maxY) {
            throw new IllegalArgumentException("Min y cannot be greater than max y");
        }
    }

    public static TileRectangle between(RedTilePosition first, RedTilePosition second) {
        return new TileRectangle(
                Math.min(first.x(), second.x()),
                Math.max(first.x(), second.x()),
                Math.min(first.y(), second.y()),
                Math.max(first.y(), second.y())
        );
    }

    public long area() {
        long width = (long) maxX - minX + 1;
        long height = (long) maxY - minY + 1;

        return width * height;
    }

    public List<RedTilePosition> corners() {
        return List.of(
                new RedTilePosition(minX, minY),
                new RedTilePosition(minX, maxY),
                new RedTilePosition(maxX, minY),
                new RedTilePosition(maxX, maxY)
        );
    }

    public boolean hasStrictInteriorColumn(int x) {
        return minX < x && x < maxX;
    }

    public boolean hasStrictInteriorRow(int y) {
        return minY < y && y < maxY;
    }

    public boolean verticalRangeIntersectsInterior(int firstY, int secondY) {
        return rangeIntersectsInterior(firstY, secondY, minY, maxY);
    }

    public boolean horizontalRangeIntersectsInterior(int firstX, int secondX) {
        return rangeIntersectsInterior(firstX, secondX, minX, maxX);
    }

    private boolean rangeIntersectsInterior(int first, int second, int min, int max) {
        int rangeMin = Math.min(first, second);
        int rangeMax = Math.max(first, second);

        int interiorMin = min + 1;
        int interiorMax = max - 1;

        return Math.max(rangeMin, interiorMin) <= Math.min(rangeMax, interiorMax);
    }
}