package es.ulpgc.aoc2025.day09.part2;

import es.ulpgc.aoc2025.day09.common.RedTileMap;
import es.ulpgc.aoc2025.day09.common.RedTilePosition;

import java.util.List;

public class LargestRedGreenRectangleAnalyzer {

    public long largestRectangleAreaIn(RedTileMap map) {
        List<RedTilePosition> redTiles = map.redTiles();
        RedGreenTileRegion region = new RedGreenTileRegion(redTiles);

        long largestArea = 0;

        for (int first = 0; first < redTiles.size() - 1; first++) {
            for (int second = first + 1; second < redTiles.size(); second++) {
                TileRectangle rectangle = TileRectangle.between(
                        redTiles.get(first),
                        redTiles.get(second)
                );

                long area = rectangle.area();

                if (area <= largestArea) {
                    continue;
                }

                if (region.contains(rectangle)) {
                    largestArea = area;
                }
            }
        }

        return largestArea;
    }
}