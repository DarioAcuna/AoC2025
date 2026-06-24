package es.ulpgc.aoc2025.day06.part1;

import es.ulpgc.aoc2025.day06.common.MathOperation;
import es.ulpgc.aoc2025.day06.common.MathProblem;
import es.ulpgc.aoc2025.day06.common.MathWorksheet;

import java.util.ArrayList;
import java.util.List;

public class HorizontalMathWorksheetProblemExtractor {

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

        for (int row = 0; row < worksheet.operationRowIndex(); row++) {
            String text = worksheet.textAt(row, startColumn, endColumn).trim();

            if (!text.isEmpty()) {
                numbers.add(Long.parseLong(text));
            }
        }

        return numbers;
    }

    private MathOperation extractOperationFrom(MathWorksheet worksheet, int startColumn, int endColumn) {
        String symbol = worksheet
                .textAt(worksheet.operationRowIndex(), startColumn, endColumn)
                .trim();

        return MathOperation.fromSymbol(symbol);
    }
}