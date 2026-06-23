package es.ulpgc.aoc2025.day01.common;

public class CircularDial {

    private static final int SIZE = 100;

    private int position;

    public CircularDial(int initialPosition) {
        if (initialPosition < 0 || initialPosition >= SIZE) {
            throw new IllegalArgumentException("Initial position must be between 0 and 99");
        }

        this.position = initialPosition;
    }

    public int position() {
        return position;
    }

    public void rotate(Rotation rotation) {
        if (rotation.direction() == Direction.RIGHT) {
            position = Math.floorMod(position + rotation.distance(), SIZE);
        } else {
            position = Math.floorMod(position - rotation.distance(), SIZE);
        }
    }

    public long countZeroClicksDuring(Rotation rotation) {
        int firstDistanceToZero = firstDistanceToZero(rotation.direction());

        if (rotation.distance() < firstDistanceToZero) {
            rotate(rotation);
            return 0;
        }

        long zeroClicks = 1L + (rotation.distance() - firstDistanceToZero) / SIZE;
        rotate(rotation);
        return zeroClicks;
    }

    private int firstDistanceToZero(Direction direction) {
        if (direction == Direction.RIGHT) {
            int distance = Math.floorMod(0 - position, SIZE);
            return distance == 0 ? SIZE : distance;
        }

        int distance = Math.floorMod(position, SIZE);
        return distance == 0 ? SIZE : distance;
    }
}
