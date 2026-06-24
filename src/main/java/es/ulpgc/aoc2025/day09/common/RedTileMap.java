package es.ulpgc.aoc2025.day09.common;

import java.util.List;

public record RedTileMap(List<RedTilePosition> redTiles) {

    public RedTileMap {
        if (redTiles == null) {
            throw new IllegalArgumentException("Red tiles cannot be null");
        }

        if (redTiles.size() < 2) {
            throw new IllegalArgumentException("At least two red tiles are required");
        }

        redTiles = List.copyOf(redTiles);
    }

    public int size() {
        return redTiles.size();
    }

    public RedTilePosition redTileAt(int index) {
        return redTiles.get(index);
    }
}