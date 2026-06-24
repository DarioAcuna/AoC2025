package es.ulpgc.aoc2025.day08.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JunctionBoxConnectionGenerator {

    public List<JunctionBoxConnection> sortedConnectionsOf(JunctionBoxMap map) {
        List<JunctionBoxConnection> connections = new ArrayList<>();

        for (int first = 0; first < map.size() - 1; first++) {
            for (int second = first + 1; second < map.size(); second++) {
                long squaredDistance = map.positionAt(first)
                        .squaredDistanceTo(map.positionAt(second));

                connections.add(new JunctionBoxConnection(first, second, squaredDistance));
            }
        }

        connections.sort(Comparator.naturalOrder());

        return connections;
    }
}