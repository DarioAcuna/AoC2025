package es.ulpgc.aoc2025.day04.part1;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day04.common.PaperRollDiagram;
import es.ulpgc.aoc2025.day04.common.PaperRollDiagramParser;

import java.util.List;

public class Day04Part1Solver implements PuzzleSolver {

    private static final int ACCESSIBLE_LIMIT = 4;

    private final PaperRollDiagramParser parser = new PaperRollDiagramParser();

    @Override
    public long solve(List<String> lines) {
        PaperRollDiagram diagram = parser.parse(lines);

        long accessiblePaperRolls = 0;

        for (int row = 0; row < diagram.height(); row++) {
            for (int column = 0; column < diagram.width(); column++) {
                if (isAccessiblePaperRoll(diagram, row, column)) {
                    accessiblePaperRolls++;
                }
            }
        }

        return accessiblePaperRolls;
    }

    private boolean isAccessiblePaperRoll(PaperRollDiagram diagram, int row, int column) {
        return diagram.hasPaperRollAt(row, column)
                && adjacentPaperRollsOf(diagram, row, column) < ACCESSIBLE_LIMIT;
    }

    private int adjacentPaperRollsOf(PaperRollDiagram diagram, int row, int column) {
        int adjacentPaperRolls = 0;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }

                int adjacentRow = row + rowOffset;
                int adjacentColumn = column + columnOffset;

                if (isPaperRollAt(diagram, adjacentRow, adjacentColumn)) {
                    adjacentPaperRolls++;
                }
            }
        }

        return adjacentPaperRolls;
    }

    private boolean isPaperRollAt(PaperRollDiagram diagram, int row, int column) {
        return diagram.contains(row, column)
                && diagram.hasPaperRollAt(row, column);
    }
}