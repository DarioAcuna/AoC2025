package es.ulpgc.aoc2025.day08.part2;

import es.ulpgc.aoc2025.day08.common.CircuitDisjointSet;
import es.ulpgc.aoc2025.day08.common.JunctionBoxConnection;
import es.ulpgc.aoc2025.day08.common.JunctionBoxConnectionGenerator;
import es.ulpgc.aoc2025.day08.common.JunctionBoxMap;
import es.ulpgc.aoc2025.day08.common.JunctionBoxPosition;

import java.util.List;

public class FinalCircuitConnectionAnalyzer {

    private final JunctionBoxConnectionGenerator connectionGenerator = new JunctionBoxConnectionGenerator();

    public long productOfXCoordinatesOfFinalConnection(JunctionBoxMap map) {
        List<JunctionBoxConnection> connections = connectionGenerator.sortedConnectionsOf(map);
        CircuitDisjointSet circuits = new CircuitDisjointSet(map.size());

        for (JunctionBoxConnection connection : connections) {
            boolean connectedDifferentCircuits = circuits.union(
                    connection.firstIndex(),
                    connection.secondIndex()
            );

            if (!connectedDifferentCircuits) {
                continue;
            }

            if (circuits.isSingleComponent()) {
                JunctionBoxPosition first = map.positionAt(connection.firstIndex());
                JunctionBoxPosition second = map.positionAt(connection.secondIndex());

                return (long) first.x() * second.x();
            }
        }

        throw new IllegalStateException("The junction boxes could not be connected into a single circuit");
    }
}