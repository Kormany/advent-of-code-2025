package io.github.kormany.aoc2025.solutions;

import java.util.*;

public class Day11 {

    private static class Device {
        String name;
        String[] outputs;

        Device(String name, String[] outputs) {
            this.name = name;
            this.outputs = outputs;
        }
    }

    private final List<String> input;

    public Day11() {
        input = InputReader.readLines("day11.txt");
    }

    public long part1() {
        var devices = getDevices();
        return countPaths(devices, "you", "out");
    }

    public long part2() {
        var devices = getDevices();
        return countPaths2(devices,
                "svr", "out",
                "dac", "fft");
    }

    private ArrayList<Device> getDevices() {
        ArrayList<Device> devices = new ArrayList<>();
        for (String line : input) {
            String[] parts = line.split(" ");
            String name = parts[0].substring(0, parts[0].length() - 1);
            String[] outputs = Arrays.copyOfRange(parts, 1, parts.length);
            devices.add(new Device(name, outputs));
        }
        return devices;
    }

    private long countPaths(List<Device> devices,
                               String startName,
                               String targetName) {
        Set<String> allNames = new HashSet<>();
        for (Device d : devices) {
            allNames.add(d.name);
            allNames.addAll(Arrays.asList(d.outputs));
        }

        allNames.add(startName);
        allNames.add(targetName);

        Map<String, Integer> idOf = new HashMap<>();
        int id = 0;
        for (String name : allNames) idOf.put(name, id++);

        int n = allNames.size();
        int[][] graph = new int[n][];

        Arrays.fill(graph, new int[0]);

        for (Device d : devices) {
            int u = idOf.get(d.name);
            graph[u] = Arrays.stream(d.outputs).mapToInt(idOf::get).toArray();
        }

        int start = idOf.get(startName);
        int target = idOf.get(targetName);

        long[] memo = new long[n];
        Arrays.fill(memo, -1);

        BitSet visited = new BitSet(n);
        return dfsCount(graph, start, target, visited, memo);
    }

    private long dfsCount(int[][] graph,
                          int current,
                          int target,
                          BitSet visited,
                          long[] memo) {

        if (current == target) return 1;

        if (!visited.get(current) && memo[current] != -1)
            return memo[current];

        if (visited.get(current))
            return 0; // cycle avoid

        visited.set(current);

        long sum = 0;
        for (int next : graph[current]) {
            sum += dfsCount(graph, next, target, visited, memo);
        }

        visited.clear(current);
        memo[current] = sum;
        return sum;
    }

    private long countPaths2(List<Device> devices,
                           String startName,
                           String targetName,
                           String A,
                           String B) {
        long total = 0;

        long p1 = countPaths(devices, startName, A);
        long p2 = countPaths(devices, A, B);
        long p3 = countPaths(devices, B, targetName);
        total += (p1 * p2 * p3);

        long q1 = countPaths(devices, startName, B);
        long q2 = countPaths(devices, B, A);
        long q3 = countPaths(devices, A, targetName);
        total += (q1 * q2 * q3);

        return total;
    }
}
