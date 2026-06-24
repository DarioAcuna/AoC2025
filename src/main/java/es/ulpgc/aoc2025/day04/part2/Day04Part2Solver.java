package es.ulpgc.aoc2025.day04.part2;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day04.common.PaperRollDiagram;
import es.ulpgc.aoc2025.day04.common.PaperRollDiagramParser;

import java.util.List;

public class Day04Part2Solver implements PuzzleSolver {

    private final PaperRollDiagramParser parser = new PaperRollDiagramParser();

    @Override
    public long solve(List<String> lines) {
        PaperRollDiagram diagram = parser.parse(lines);
        RemovablePaperRollDiagram removableDiagram = new RemovablePaperRollDiagram(diagram);

        return removableDiagram.removeAllAccessiblePaperRolls();
    }
}