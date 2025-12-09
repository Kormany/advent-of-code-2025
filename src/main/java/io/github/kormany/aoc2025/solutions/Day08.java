package io.github.kormany.aoc2025.solutions;

import java.util.*;

import static java.lang.Math.pow;

public class Day08 {
    private static class Box {
        int x;
        int y;
        int z;

        Box(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class Distance {
        Box b1;
        Box b2;
        double distance;

        Distance(Box b1, Box b2) {
            this.b1 = b1;
            this.b2 = b2;
            this.distance = distance(b1, b2);
        }
    }

    private final List<String> input;

    public Day08() {
        input = InputReader.readLines("day08.txt");
    }

    public int part1() {
        Box[] boxes = getBoxes();
        ArrayList<Distance> distances = getDistances(boxes);
        distances.sort(Comparator.comparingDouble(d -> d.distance));

        ArrayList<HashSet<Box>> connections = new ArrayList<>();

        for (int i = 0; i < Math.min(1000, distances.size()); i++) {
            Box b1 = distances.get(i).b1;
            Box b2 = distances.get(i).b2;

            HashSet<Box> connection = new HashSet<>();
            connection.add(b1);
            connection.add(b2);

            Set<HashSet<Box>> toRemove = new HashSet<>();
            for (HashSet<Box> c : connections) {
                if (c.contains(b1) || c.contains(b2)) {
                    connection.addAll(c);
                    toRemove.add(c);
                }
            }

            connections.removeAll(toRemove);
            connections.add(connection);
        }

        connections.sort(Comparator.comparingInt((HashSet<Box> c)
                -> c.size()).reversed());
        int product = 1;
        for (int i = 0; i < Math.min(3, connections.size()); i++) {
            product *= connections.get(i).size();
        }

        return product;
    }

    public int part2() {
        Box[] boxes = getBoxes();
        int countBoxes = new HashSet<>(Arrays.asList(boxes)).size();
        ArrayList<Distance> distances = getDistances(boxes);
        distances.sort(Comparator.comparingDouble(d -> d.distance));
        ArrayList<HashSet<Box>> connections = new ArrayList<>();

        for (Distance distance : distances) {
            Box b1 = distance.b1;
            Box b2 = distance.b2;

            HashSet<Box> connection = new HashSet<>();
            connection.add(b1);
            connection.add(b2);

            Set<HashSet<Box>> toRemove = new HashSet<>();
            for (HashSet<Box> c : connections) {
                if (c.contains(b1) || c.contains(b2)) {
                    connection.addAll(c);
                    toRemove.add(c);
                }
            }

            connections.removeAll(toRemove);
            connections.add(connection);
            connections.sort(Comparator.comparingInt((HashSet<Box> c)
                    -> c.size()).reversed());
            if (connections.getFirst().size() == countBoxes) {
                return b1.x * b2.x;
            }
        }
        return 0;
    }

    private Box[] getBoxes() {
        Box[] boxes = new Box[input.size()];
        String[] coordinates;
        for (int i = 0; i < input.size(); i++) {
            coordinates = input.get(i).split(",");
            if (coordinates.length == 3) {
                boxes[i] = new Box(
                        Integer.parseInt(coordinates[0]),
                        Integer.parseInt(coordinates[1]),
                        Integer.parseInt(coordinates[2])
                );
            }
        }
        return boxes;
    }

    private ArrayList<Distance> getDistances(Box[] boxes) {
        ArrayList<Distance> distances = new ArrayList<>();
        for (int i = 0; i < boxes.length - 1; i++) {
            for (int j = i + 1; j < boxes.length; j++) {
                distances.add(new Distance(boxes[i], boxes[j]));
            }
        }
        return distances;
    }

    private static double distance(Box b1, Box b2) {
        return (
                pow(b2.x - b1.x, 2) +
                pow(b2.y - b1.y, 2) +
                pow(b2.z - b1.z, 2)
        );
    }
}
