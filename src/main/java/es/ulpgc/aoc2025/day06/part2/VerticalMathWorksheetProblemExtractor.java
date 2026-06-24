package es.ulpgc.aoc2025.day06.part2;

import es.ulpgc.aoc2025.day06.common.MathOperation;
import es.ulpgc.aoc2025.day06.common.MathProblem;
import es.ulpgc.aoc2025.day06.common.MathWorksheet;

import java.util.ArrayList;
import java.util.List;

public class VerticalMathWorksheetProblemExtractor {

    public List<MathProblem> extractFrom(MathWorksheet worksheet) {
        List<MathProblem> problems = new ArrayList<>();

        int column = 0;

        while (column < worksheet.width()) {
            while (column < worksheet.width() && worksheet.isBlankColumn(column)) {
                column++;
            }

            if (column >= worksheet.width()) {
                break;
            }

            int startColumn = column;

            while (column < worksheet.width() && !worksheet.isBlankColumn(column)) {
                column++;
            }

            int endColumn = column;

            problems.add(extractProblemFrom(worksheet, startColumn, endColumn));
        }

        return problems;
    }

    private MathProblem extractProblemFrom(MathWorksheet worksheet, int startColumn, int endColumn) {
        List<Long> numbers = extractNumbersFrom(worksheet, startColumn, endColumn);
        MathOperation operation = extractOperationFrom(worksheet, startColumn, endColumn);

        return new MathProblem(numbers, operation);
    }

    private List<Long> extractNumbersFrom(MathWorksheet worksheet, int startColumn, int endColumn) {
        List<Long> numbers = new ArrayList<>();

        for (int column = endColumn - 1; column >= startColumn; column--) {
            String number = readNumberInColumn(worksheet, column);

            if (!number.isEmpty()) {
                numbers.add(Long.parseLong(number));
            }
        }

        return numbers;
    }

    private String readNumberInColumn(MathWorksheet worksheet, int column) {
        StringBuilder number = new StringBuilder();

        for (int row = 0; row < worksheet.operationRowIndex(); row++) {
            char character = worksheet.characterAt(row, column);

            if (Character.isDigit(character)) {
                number.append(character);
            }
        }

        return number.toString();
    }

    private MathOperation extractOperationFrom(MathWorksheet worksheet, int startColumn, int endColumn) {
        String symbol = worksheet
                .textAt(worksheet.operationRowIndex(), startColumn, endColumn)
                .trim();

        return MathOperation.fromSymbol(symbol);
    }
}