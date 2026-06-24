package es.ulpgc.aoc2025.day04.part2;

import es.ulpgc.aoc2025.day04.common.PaperRollDiagram;

import java.util.ArrayDeque;
import java.util.Queue;

public class RemovablePaperRollDiagram {

    private static final int ACCESSIBLE_LIMIT = 4;

    private final boolean[][] paperRolls;
    private final int[][] adjacentPaperRolls;
    private final int height;
    private final int width;

    public RemovablePaperRollDiagram(PaperRollDiagram diagram) {
        this.height = diagram.height();
        this.width = diagram.width();
        this.paperRolls = new boolean[height][width];
        this.adjacentPaperRolls = new int[height][width];

        copyPaperRollsFrom(diagram);
        initializeAdjacentPaperRolls();
    }

    public long removeAllAccessiblePaperRolls() {
        Queue<Position> accessiblePositions = initialAccessiblePositions();
        long removedPaperRolls = 0;

        while (!accessiblePositions.isEmpty()) {
            Position position = accessiblePositions.poll();

            if (!hasPaperRollAt(position)) {
                continue;
            }

            if (!isAccessible(position)) {
                continue;
            }

            removePaperRollAt(position);
            removedPaperRolls++;

            addNewAccessibleNeighboursOf(position, accessiblePositions);
        }

        return removedPaperRolls;
    }

    private void copyPaperRollsFrom(PaperRollDiagram diagram) {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                paperRolls[row][column] = diagram.hasPaperRollAt(row, column);
            }
        }
    }

    private void initializeAdjacentPaperRolls() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (paperRolls[row][column]) {
                    adjacentPaperRolls[row][column] = countAdjacentPaperRollsAt(row, column);
                }
            }
        }
    }

    private Queue<Position> initialAccessiblePositions() {
        Queue<Position> positions = new ArrayDeque<>();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Position position = new Position(row, column);

                if (hasPaperRollAt(position) && isAccessible(position)) {
                    positions.add(position);
                }
            }
        }

        return positions;
    }

    private void removePaperRollAt(Position position) {
        paperRolls[position.row()][position.column()] = false;

        forEachNeighbourOf(position, neighbour -> {
            if (hasPaperRollAt(neighbour)) {
                adjacentPaperRolls[neighbour.row()][neighbour.column()]--;
            }
        });
    }

    private void addNewAccessibleNeighboursOf(Position position, Queue<Position> accessiblePositions) {
        forEachNeighbourOf(position, neighbour -> {
            if (hasPaperRollAt(neighbour) && isAccessible(neighbour)) {
                accessiblePositions.add(neighbour);
            }
        });
    }

    private boolean isAccessible(Position position) {
        return adjacentPaperRolls[position.row()][position.column()] < ACCESSIBLE_LIMIT;
    }

    private int countAdjacentPaperRollsAt(int row, int column) {
        int adjacentPaperRolls = 0;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }

                Position neighbour = new Position(row + rowOffset, column + columnOffset);

                if (hasPaperRollAt(neighbour)) {
                    adjacentPaperRolls++;
                }
            }
        }

        return adjacentPaperRolls;
    }

    private void forEachNeighbourOf(Position position, NeighbourAction action) {
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }

                Position neighbour = new Position(
                        position.row() + rowOffset,
                        position.column() + columnOffset
                );

                if (contains(neighbour)) {
                    action.apply(neighbour);
                }
            }
        }
    }

    private boolean hasPaperRollAt(Position position) {
        return contains(position)
                && paperRolls[position.row()][position.column()];
    }

    private boolean contains(Position position) {
        return 0 <= position.row() && position.row() < height
                && 0 <= position.column() && position.column() < width;
    }

    private record Position(int row, int column) {
    }

    private interface NeighbourAction {
        void apply(Position position);
    }
}