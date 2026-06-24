package es.ulpgc.aoc2025.day06.common;

import java.util.List;

public record MathProblem(List<Long> numbers, MathOperation operation) {

    public MathProblem {
        if (numbers == null) {
            throw new IllegalArgumentException("Numbers cannot be null");
        }

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("A problem must contain at least one number");
        }

        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }

        numbers = List.copyOf(numbers);
    }

    public long solve() {
        long result = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            result = operation.applyTo(result, numbers.get(i));
        }

        return result;
    }
}