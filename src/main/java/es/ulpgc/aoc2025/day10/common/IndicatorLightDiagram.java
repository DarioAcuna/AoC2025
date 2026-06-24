package es.ulpgc.aoc2025.day10.common;

public record IndicatorLightDiagram(String pattern, int targetMask) {

    public IndicatorLightDiagram {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }

        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be empty");
        }

        if (!pattern.matches("[.#]+")) {
            throw new IllegalArgumentException("Pattern can only contain '.' and '#'");
        }

        if (targetMask < 0) {
            throw new IllegalArgumentException("Target mask cannot be negative");
        }
    }

    public static IndicatorLightDiagram from(String pattern) {
        int targetMask = 0;

        for (int light = 0; light < pattern.length(); light++) {
            if (pattern.charAt(light) == '#') {
                targetMask |= 1 << light;
            }
        }

        return new IndicatorLightDiagram(pattern, targetMask);
    }

    public int lightCount() {
        return pattern.length();
    }
}