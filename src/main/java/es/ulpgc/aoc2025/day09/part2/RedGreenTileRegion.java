package es.ulpgc.aoc2025.day09.part2;

import es.ulpgc.aoc2025.day09.common.RedTilePosition;

import java.util.List;

public class RedGreenTileRegion {

    private final List<RedTilePosition> boundary;

    public RedGreenTileRegion(List<RedTilePosition> boundary) {
        if (boundary == null) {
            throw new IllegalArgumentException("Boundary cannot be null");
        }

        if (boundary.size() < 4) {
            throw new IllegalArgumentException("Boundary must contain at least four points");
        }

        this.boundary = List.copyOf(boundary);
    }

    public boolean contains(TileRectangle rectangle) {
        return containsAllCornersOf(rectangle)
                && !hasBoundaryInside(rectangle);
    }

    private boolean containsAllCornersOf(TileRectangle rectangle) {
        for (RedTilePosition corner : rectangle.corners()) {
            if (!contains(corner)) {
                return false;
            }
        }

        return true;
    }

    private boolean contains(RedTilePosition position) {
        return isOnBoundary(position) || isInside(position);
    }

    private boolean isOnBoundary(RedTilePosition position) {
        for (int i = 0; i < boundary.size(); i++) {
            RedTilePosition first = boundary.get(i);
            RedTilePosition second = boundary.get((i + 1) % boundary.size());

            if (isOnSegment(position, first, second)) {
                return true;
            }
        }

        return false;
    }

    private boolean isOnSegment(
            RedTilePosition position,
            RedTilePosition first,
            RedTilePosition second
    ) {
        if (first.x() == second.x()) {
            return position.x() == first.x()
                    && between(position.y(), first.y(), second.y());
        }

        if (first.y() == second.y()) {
            return position.y() == first.y()
                    && between(position.x(), first.x(), second.x());
        }

        throw new IllegalArgumentException("Boundary segment must be horizontal or vertical");
    }

    private boolean between(int value, int first, int second) {
        return Math.min(first, second) <= value
                && value <= Math.max(first, second);
    }

    private boolean isInside(RedTilePosition position) {
        boolean inside = false;

        for (int i = 0; i < boundary.size(); i++) {
            RedTilePosition first = boundary.get(i);
            RedTilePosition second = boundary.get((i + 1) % boundary.size());

            if (crossesHorizontalRay(position, first, second)) {
                inside = !inside;
            }
        }

        return inside;
    }

    private boolean crossesHorizontalRay(
            RedTilePosition position,
            RedTilePosition first,
            RedTilePosition second
    ) {
        if (first.y() == second.y()) {
            return false;
        }

        boolean crossesY = (first.y() > position.y()) != (second.y() > position.y());

        if (!crossesY) {
            return false;
        }

        int intersectionX = first.x();

        return position.x() < intersectionX;
    }

    private boolean hasBoundaryInside(TileRectangle rectangle) {
        for (int i = 0; i < boundary.size(); i++) {
            RedTilePosition first = boundary.get(i);
            RedTilePosition second = boundary.get((i + 1) % boundary.size());

            if (segmentPassesThroughInterior(rectangle, first, second)) {
                return true;
            }
        }

        return false;
    }

    private boolean segmentPassesThroughInterior(
            TileRectangle rectangle,
            RedTilePosition first,
            RedTilePosition second
    ) {
        if (first.x() == second.x()) {
            return rectangle.hasStrictInteriorColumn(first.x())
                    && rectangle.verticalRangeIntersectsInterior(first.y(), second.y());
        }

        if (first.y() == second.y()) {
            return rectangle.hasStrictInteriorRow(first.y())
                    && rectangle.horizontalRangeIntersectsInterior(first.x(), second.x());
        }

        throw new IllegalArgumentException("Boundary segment must be horizontal or vertical");
    }
}