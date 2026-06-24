package es.ulpgc.aoc2025.day08.common;

public record JunctionBoxConnection(
        int firstIndex,
        int secondIndex,
        long squaredDistance
) implements Comparable<JunctionBoxConnection> {

    @Override
    public int compareTo(JunctionBoxConnection other) {
        int distanceComparison = Long.compare(this.squaredDistance, other.squaredDistance);

        if (distanceComparison != 0) {
            return distanceComparison;
        }

        int firstIndexComparison = Integer.compare(this.firstIndex, other.firstIndex);

        if (firstIndexComparison != 0) {
            return firstIndexComparison;
        }

        return Integer.compare(this.secondIndex, other.secondIndex);
    }
}