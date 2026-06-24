package es.ulpgc.aoc2025.day03.common;

import java.util.ArrayList;
import java.util.List;

public class BatteryBankParser {

    public List<BatteryBank> parse(List<String> lines) {
        List<BatteryBank> banks = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            banks.add(new BatteryBank(line.trim()));
        }

        return banks;
    }
}