package es.ulpgc.aoc2025.day10.part2;

import es.ulpgc.aoc2025.day10.common.ButtonWiring;
import es.ulpgc.aoc2025.day10.common.Machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumJoltagePressCalculator {

    private static final double EPSILON = 1e-7;

    public long minimumPressesFor(Machine machine) {
        LinearSystem system = buildSystemFrom(machine);
        ReducedSystem reducedSystem = reduce(system);

        return new IntegerSolutionSearcher(
                machine,
                reducedSystem
        ).minimumPresses();
    }

    private LinearSystem buildSystemFrom(Machine machine) {
        int counterCount = machine.counterCount();
        int buttonCount = machine.buttons().size();

        double[][] matrix = new double[counterCount][buttonCount + 1];

        for (int counter = 0; counter < counterCount; counter++) {
            matrix[counter][buttonCount] = machine.joltageRequirements().get(counter);
        }

        for (int button = 0; button < buttonCount; button++) {
            ButtonWiring wiring = machine.buttons().get(button);

            for (int counter = 0; counter < counterCount; counter++) {
                if (wiring.affects(counter)) {
                    matrix[counter][button] = 1;
                }
            }
        }

        return new LinearSystem(matrix, counterCount, buttonCount);
    }

    private ReducedSystem reduce(LinearSystem system) {
        double[][] matrix = copyOf(system.matrix());
        List<Integer> pivotColumns = new ArrayList<>();

        int row = 0;

        for (int column = 0; column < system.variableCount() && row < system.equationCount(); column++) {
            int pivotRow = findPivotRow(matrix, row, column, system.equationCount());

            if (pivotRow == -1) {
                continue;
            }

            swapRows(matrix, row, pivotRow);
            normalizePivotRow(matrix, row, column, system.variableCount());
            eliminateColumn(matrix, row, column, system.equationCount(), system.variableCount());

            pivotColumns.add(column);
            row++;
        }

        ensureSystemIsConsistent(matrix, row, system);

        return new ReducedSystem(matrix, pivotColumns, row, system.variableCount());
    }

    private int findPivotRow(double[][] matrix, int startRow, int column, int equationCount) {
        int pivotRow = -1;
        double bestValue = 0;

        for (int row = startRow; row < equationCount; row++) {
            double value = Math.abs(matrix[row][column]);

            if (value > bestValue) {
                bestValue = value;
                pivotRow = row;
            }
        }

        return bestValue < EPSILON ? -1 : pivotRow;
    }

    private void swapRows(double[][] matrix, int firstRow, int secondRow) {
        double[] temporary = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = temporary;
    }

    private void normalizePivotRow(double[][] matrix, int row, int pivotColumn, int variableCount) {
        double pivot = matrix[row][pivotColumn];

        for (int column = pivotColumn; column <= variableCount; column++) {
            matrix[row][column] /= pivot;
        }
    }

    private void eliminateColumn(
            double[][] matrix,
            int pivotRow,
            int pivotColumn,
            int equationCount,
            int variableCount
    ) {
        for (int row = 0; row < equationCount; row++) {
            if (row == pivotRow) {
                continue;
            }

            double factor = matrix[row][pivotColumn];

            if (Math.abs(factor) < EPSILON) {
                continue;
            }

            for (int column = pivotColumn; column <= variableCount; column++) {
                matrix[row][column] -= factor * matrix[pivotRow][column];
            }
        }
    }

    private void ensureSystemIsConsistent(double[][] matrix, int rank, LinearSystem system) {
        for (int row = rank; row < system.equationCount(); row++) {
            boolean emptyEquation = true;

            for (int column = 0; column < system.variableCount(); column++) {
                if (Math.abs(matrix[row][column]) >= EPSILON) {
                    emptyEquation = false;
                    break;
                }
            }

            if (emptyEquation && Math.abs(matrix[row][system.variableCount()]) >= EPSILON) {
                throw new IllegalStateException("Machine cannot be configured");
            }
        }
    }

    private double[][] copyOf(double[][] matrix) {
        double[][] copy = new double[matrix.length][];

        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row].clone();
        }

        return copy;
    }

    private record LinearSystem(double[][] matrix, int equationCount, int variableCount) {
    }

    private record ReducedSystem(
            double[][] matrix,
            List<Integer> pivotColumns,
            int rank,
            int variableCount
    ) {

        public List<Integer> freeColumns() {
            Set<Integer> pivots = new HashSet<>(pivotColumns);
            List<Integer> freeColumns = new ArrayList<>();

            for (int column = 0; column < variableCount; column++) {
                if (!pivots.contains(column)) {
                    freeColumns.add(column);
                }
            }

            return freeColumns;
        }
    }

    private static class IntegerSolutionSearcher {

        private final Machine machine;
        private final ReducedSystem system;
        private final List<Integer> freeColumns;
        private final long[] currentSolution;

        private long bestPresses = Long.MAX_VALUE;

        private IntegerSolutionSearcher(Machine machine, ReducedSystem system) {
            this.machine = machine;
            this.system = system;
            this.freeColumns = system.freeColumns();
            this.currentSolution = new long[system.variableCount()];
        }

        public long minimumPresses() {
            search(0);

            if (bestPresses == Long.MAX_VALUE) {
                throw new IllegalStateException("Machine cannot be configured");
            }

            return bestPresses;
        }

        private void search(int freeColumnIndex) {
            if (freeColumnIndex == freeColumns.size()) {
                tryCompleteSolution();
                return;
            }

            int column = freeColumns.get(freeColumnIndex);
            long upperBound = upperBoundFor(column);

            for (long value = 0; value <= upperBound; value++) {
                currentSolution[column] = value;
                search(freeColumnIndex + 1);
            }

            currentSolution[column] = 0;
        }

        private long upperBoundFor(int buttonIndex) {
            ButtonWiring button = machine.buttons().get(buttonIndex);

            long upperBound = Long.MAX_VALUE;

            for (int counter = 0; counter < machine.counterCount(); counter++) {
                if (button.affects(counter)) {
                    upperBound = Math.min(
                            upperBound,
                            machine.joltageRequirements().get(counter)
                    );
                }
            }

            return upperBound == Long.MAX_VALUE ? 0 : upperBound;
        }

        private void tryCompleteSolution() {
            double[][] matrix = system.matrix();

            for (int row = system.rank() - 1; row >= 0; row--) {
                int pivotColumn = system.pivotColumns().get(row);
                double value = matrix[row][system.variableCount()];

                for (int freeColumn : freeColumns) {
                    value -= matrix[row][freeColumn] * currentSolution[freeColumn];
                }

                long roundedValue = Math.round(value);

                if (Math.abs(value - roundedValue) > EPSILON) {
                    return;
                }

                if (roundedValue < 0) {
                    return;
                }

                currentSolution[pivotColumn] = roundedValue;
            }

            long presses = totalPresses();

            if (presses < bestPresses) {
                bestPresses = presses;
            }
        }

        private long totalPresses() {
            long total = 0;

            for (long presses : currentSolution) {
                total += presses;
            }

            return total;
        }
    }
}