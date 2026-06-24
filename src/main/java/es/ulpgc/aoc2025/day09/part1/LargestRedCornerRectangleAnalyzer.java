package es.ulpgc.aoc2025.day09.part1;

import es.ulpgc.aoc2025.day09.common.RedTileMap;
import es.ulpgc.aoc2025.day09.common.RedTilePosition;

public class LargestRedCornerRectangleAnalyzer {

    public long largestRectangleAreaIn(RedTileMap map) {
        long largestArea = 0;

        for (int first = 0; first < map.size() - 1; first++) {
            for (int second = first + 1; second < map.size(); second++) {
                RedTilePosition firstTile = map.redTileAt(first);
                RedTilePosition secondTile = map.redTileAt(second);

                long area = firstTile.rectangleAreaWith(secondTile);

                if (area > largestArea) {
                    largestArea = area;
                }
            }
        }

        return largestArea;
    }
}