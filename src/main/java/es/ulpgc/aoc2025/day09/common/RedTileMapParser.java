package es.ulpgc.aoc2025.day09.common;

import java.util.ArrayList;
import java.util.List;

public class RedTileMapParser {

    public RedTileMap parse(List<String> lines) {
        List<RedTilePosition> redTiles = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            redTiles.add(parsePosition(line.trim()));
        }

        return new RedTileMap(redTiles);
    }

    private RedTilePosition parsePosition(String line) {
        String[] coordinates = line.split(",");

        if (coordinates.length != 2) {
            throw new IllegalArgumentException("Invalid red tile position: " + line);
        }

        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);

        return new RedTilePosition(x, y);
    }
}