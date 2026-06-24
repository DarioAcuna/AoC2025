package es.ulpgc.aoc2025.day08.part1;

import es.ulpgc.aoc2025.day08.common.CircuitDisjointSet;
import es.ulpgc.aoc2025.day08.common.JunctionBoxConnection;
import es.ulpgc.aoc2025.day08.common.JunctionBoxConnectionGenerator;
import es.ulpgc.aoc2025.day08.common.JunctionBoxMap;

import java.util.Comparator;
import java.util.List;

public class ShortestConnectionCircuitAnalyzer {

    private final JunctionBoxConnectionGenerator connectionGenerator = new JunctionBoxConnectionGenerator();

    public long productOfThreeLargestCircuitSizesAfter(
            JunctionBoxMap map,
            int connectionsToMake
    ) {
        List<JunctionBoxConnection> connections = connectionGenerator.sortedConnectionsOf(map);

        CircuitDisjointSet circuits = new CircuitDisjointSet(map.size());

        int limit = Math.min(connectionsToMake, connections.size());

        for (int i = 0; i < limit; i++) {
            JunctionBoxConnection connection = connections.get(i);

            circuits.union(
                    connection.firstIndex(),
                    connection.secondIndex()
            );
        }

        return productOfThreeLargestSizes(circuits.componentSizes());
    }

    private long productOfThreeLargestSizes(List<Integer> sizes) {
        return sizes.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Integer::longValue)
                .reduce(1L, (left, right) -> left * right);
    }
}