package es.ulpgc.aoc2025.day06.common;

public enum MathOperation {
    ADD,
    MULTIPLY;

    public long applyTo(long left, long right) {
        return switch (this) {
            case ADD -> left + right;
            case MULTIPLY -> left * right;
        };
    }

    public static MathOperation fromSymbol(String symbol) {
        return switch (symbol) {
            case "+" -> ADD;
            case "*" -> MULTIPLY;
            default -> throw new IllegalArgumentException("Invalid operation: " + symbol);
        };
    }
}