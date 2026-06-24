package es.ulpgc.aoc2025.day10.part1;

import es.ulpgc.aoc2025.day10.common.ButtonWiring;
import es.ulpgc.aoc2025.day10.common.Machine;

import java.util.Arrays;

public class MinimumButtonPressCalculator {

    private static final int UNREACHABLE = Integer.MAX_VALUE / 2;

    public int minimumPressesFor(Machine machine) {
        int states = 1 << machine.lightCount();

        int[] minimumPresses = new int[states];
        Arrays.fill(minimumPresses, UNREACHABLE);
        minimumPresses[0] = 0;

        for (ButtonWiring button : machine.buttons()) {
            int[] nextMinimumPresses = minimumPresses.clone();

            for (int state = 0; state < states; state++) {
                if (minimumPresses[state] == UNREACHABLE) {
                    continue;
                }

                int nextState = state ^ button.toggledLightsMask();

                nextMinimumPresses[nextState] = Math.min(
                        nextMinimumPresses[nextState],
                        minimumPresses[state] + 1
                );
            }

            minimumPresses = nextMinimumPresses;
        }

        int targetState = machine.diagram().targetMask();
        int result = minimumPresses[targetState];

        if (result == UNREACHABLE) {
            throw new IllegalStateException("Machine cannot be configured");
        }

        return result;
    }
}