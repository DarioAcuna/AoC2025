package es.ulpgc.aoc2025.day10.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineManualParser {

    private static final Pattern DIAGRAM_PATTERN = Pattern.compile("\\[([.#]+)]");
    private static final Pattern BUTTON_PATTERN = Pattern.compile("\\(([^)]*)\\)");
    private static final Pattern JOLTAGGE_PATTERN = Pattern.compile("\\{([^}]*)}");

    public MachineManual parse(List<String> lines) {
        List<Machine> machines = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            machines.add(parseMachine(line.trim()));
        }

        return new MachineManual(machines);
    }

    private Machine parseMachine(String line) {
        IndicatorLightDiagram diagram = parseDiagram(line);
        List<ButtonWiring> buttons = parseButtons(line, diagram.lightCount());
        List<Integer> joltageRequirements = parseJoltageRequirements(line);

        return new Machine(diagram, buttons, joltageRequirements);
    }

    private IndicatorLightDiagram parseDiagram(String line) {
        Matcher matcher = DIAGRAM_PATTERN.matcher(line);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Missing indicator light diagram: " + line);
        }

        return IndicatorLightDiagram.from(matcher.group(1));
    }

    private List<ButtonWiring> parseButtons(String line, int lightCount) {
        Matcher matcher = BUTTON_PATTERN.matcher(line);
        List<ButtonWiring> buttons = new ArrayList<>();

        while (matcher.find()) {
            buttons.add(parseButton(matcher.group(1), lightCount));
        }

        return buttons;
    }

    private ButtonWiring parseButton(String text, int lightCount) {
        int mask = 0;

        if (text.isBlank()) {
            return new ButtonWiring(mask);
        }

        String[] indexes = text.split(",");

        for (String rawIndex : indexes) {
            int lightIndex = Integer.parseInt(rawIndex.trim());

            if (lightIndex < 0 || lightIndex >= lightCount) {
                throw new IllegalArgumentException("Invalid light index: " + lightIndex);
            }

            mask |= 1 << lightIndex;
        }

        return new ButtonWiring(mask);
    }

    private List<Integer> parseJoltageRequirements(String line) {
        Matcher matcher = JOLTAGGE_PATTERN.matcher(line);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Missing joltage requirements: " + line);
        }

        String[] values = matcher.group(1).split(",");
        List<Integer> requirements = new ArrayList<>();

        for (String value : values) {
            requirements.add(Integer.parseInt(value.trim()));
        }

        return requirements;
    }
}