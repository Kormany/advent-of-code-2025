package io.github.kormany.aoc2025.solutions;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

    private static class Shape {
        protected int id;
        protected int size;
        protected int[][] parts;

        protected Shape(int id, ArrayList<String> parts) {
            this.id = id;
            this.size = 0;
            this.parts = new int[parts.size()][parts.getFirst().length()];
            for (int i = 0; i < parts.size(); i++) {
                for (int j = 0; j < parts.getFirst().length(); j++) {
                    if (parts.get(i).charAt(j) == '.') this.parts[i][j] = 0;
                    else if (parts.get(i).charAt(j) == '#') {
                        this.parts[i][j] = 1;
                        this.size++;
                    }
                }
            }
        }
    }

    private static class Region {
        protected int height;
        protected int width;
        protected int[] shapeQuantities;

        protected Region(int height, int width, int[] shapeQuantities) {
            this.height = height;
            this.width = width;
            this.shapeQuantities = shapeQuantities;
        }
    }

    private final List<String> input;
    private final ArrayList<Shape> shapes;
    private final ArrayList<Region> regions;

    public Day12() {
        input = InputReader.readLines("day12.txt");
        shapes = getShapes();
        regions = getRegions();
    }

    private ArrayList<Shape> getShapes() {
        ArrayList<Shape> result = new ArrayList<>();
        int idx = 0;

        while (idx < input.size()) {
            String line = input.get(idx);
            if (line.matches("^\\d+:$")) {
                int id = Integer.parseInt(line.replace(":", ""));
                idx++;
                ArrayList<String> rows = new ArrayList<>();

                while (idx < input.size()) {
                    String row = input.get(idx);
                    if (row.matches("[.#]+")) {
                        rows.add(row);
                        idx++;
                    } else if (row.isEmpty()) {
                        idx++;
                        break;
                    } else {
                        break;
                    }
                }

                result.add(new Shape(id, rows));
            } else break;
        }

        return result;
    }

    private ArrayList<Region> getRegions() {
        ArrayList<Region> result = new ArrayList<>();
        int idx = 0;

        while (idx < input.size() && !input.get(idx).matches("\\d+x\\d+:.*")) idx++;

        for (; idx < input.size(); idx++) {
            String line = input.get(idx);
            if (!line.contains(":")) continue;

            String[] parts = line.split(":");
            String[] dims = parts[0].trim().split("x");

            int height = Integer.parseInt(dims[0]);
            int width = Integer.parseInt(dims[1]);

            String[] qs = parts[1].trim().split("\\s+");
            int[] shapeQuantities = new int[qs.length];
            for (int i = 0; i < qs.length; i++) shapeQuantities[i] = Integer.parseInt(qs[i]);

            result.add(new Region(height, width, shapeQuantities));
        }

        return result;
    }

    public int part1() {
        int regionsWithAllPresents = 0;

        for (Region region : regions) {
            int regionArea = region.height * region.width;
            int presentArea = 0;

            for (int i = 0; i < region.shapeQuantities.length; i++) {
                presentArea += region.shapeQuantities[i] * shapes.get(i).size;
            }

            if (regionArea >= presentArea) regionsWithAllPresents++;
        }

        return regionsWithAllPresents;
    }

    public String part2() {
        return "---";
    }
}
