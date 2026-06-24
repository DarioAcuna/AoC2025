package es.ulpgc.aoc2025.day08.common;

public record JunctionBoxPosition(int x, int y, int z) {

    public long squaredDistanceTo(JunctionBoxPosition other) {
        long dx = (long) x - other.x;
        long dy = (long) y - other.y;
        long dz = (long) z - other.z;

        return dx * dx + dy * dy + dz * dz;
    }
}