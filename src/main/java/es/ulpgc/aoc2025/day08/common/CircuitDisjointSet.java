package es.ulpgc.aoc2025.day08.common;

import java.util.ArrayList;
import java.util.List;

public class CircuitDisjointSet {

    private final int[] parent;
    private final int[] size;

    // Añadido para parte 2.
    // Permite saber cuándo todas las cajas forman un único circuito.
    private int componentCount;

    public CircuitDisjointSet(int elements) {
        if (elements <= 0) {
            throw new IllegalArgumentException("Elements must be positive");
        }

        this.parent = new int[elements];
        this.size = new int[elements];
        this.componentCount = elements;

        for (int i = 0; i < elements; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public boolean union(int first, int second) {
        int firstRoot = find(first);
        int secondRoot = find(second);

        if (firstRoot == secondRoot) {
            return false;
        }

        if (size[firstRoot] < size[secondRoot]) {
            parent[firstRoot] = secondRoot;
            size[secondRoot] += size[firstRoot];
        } else {
            parent[secondRoot] = firstRoot;
            size[firstRoot] += size[secondRoot];
        }

        componentCount--;
        return true;
    }

    public boolean isSingleComponent() {
        return componentCount == 1;
    }

    public List<Integer> componentSizes() {
        List<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < parent.length; i++) {
            if (find(i) == i) {
                sizes.add(size[i]);
            }
        }

        return sizes;
    }

    private int find(int element) {
        if (parent[element] != element) {
            parent[element] = find(parent[element]);
        }

        return parent[element];
    }
}