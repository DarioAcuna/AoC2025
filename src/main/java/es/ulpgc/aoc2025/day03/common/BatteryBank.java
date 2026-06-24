package es.ulpgc.aoc2025.day03.common;

public record BatteryBank(String batteries) {

    public BatteryBank {
        if (batteries == null) {
            throw new IllegalArgumentException("Batteries cannot be null");
        }

        if (batteries.length() < 2) {
            throw new IllegalArgumentException("A battery bank must contain at least two batteries");
        }

        if (!batteries.matches("[1-9]+")) {
            throw new IllegalArgumentException("A battery bank can only contain digits from 1 to 9");
        }
    }
}