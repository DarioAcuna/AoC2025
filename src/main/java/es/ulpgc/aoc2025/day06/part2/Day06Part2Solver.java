package es.ulpgc.aoc2025.day06.part2;

import es.ulpgc.aoc2025.common.PuzzleSolver;
import es.ulpgc.aoc2025.day06.common.MathProblem;
import es.ulpgc.aoc2025.day06.common.MathWorksheet;
import es.ulpgc.aoc2025.day06.common.MathWorksheetParser;

import java.util.List;

public class Day06Part2Solver implements PuzzleSolver {

    private final MathWorksheetParser parser = new MathWorksheetParser();
    private final VerticalMathWorksheetProblemExtractor extractor = new VerticalMathWorksheetProblemExtractor();

    @Override
    public long solve(List<String> lines) {
        MathWorksheet worksheet = parser.parse(lines);
        List<MathProblem> problems = extractor.extractFrom(worksheet);

        long grandTotal = 0;

        for (MathProblem problem : problems) {
            grandTotal += problem.solve();
        }

        return grandTotal;
    }
}