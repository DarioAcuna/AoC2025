package es.ulpgc.aoc2025.day03.common;

public class MaximumJoltageCalculator {

    public long calculate(BatteryBank bank, int batteriesToTurnOn) {
        if (batteriesToTurnOn <= 0) {
            throw new IllegalArgumentException("Batteries to turn on must be positive");
        }

        if (batteriesToTurnOn > bank.batteries().length()) {
            throw new IllegalArgumentException("Cannot turn on more batteries than available");
        }

        String maximumJoltage = maximumSubsequence(bank.batteries(), batteriesToTurnOn);

        return Long.parseLong(maximumJoltage);
    }

    private String maximumSubsequence(String batteries, int length) {
        StringBuilder selected = new StringBuilder();
        int batteriesToRemove = batteries.length() - length;

        for (int i = 0; i < batteries.length(); i++) {
            char currentBattery = batteries.charAt(i);

            while (
                    batteriesToRemove > 0
                            && !selected.isEmpty()
                            && selected.charAt(selected.length() - 1) < currentBattery
            ) {
                selected.deleteCharAt(selected.length() - 1);
                batteriesToRemove--;
            }

            selected.append(currentBattery);
        }

        return selected.substring(0, length);
    }
}