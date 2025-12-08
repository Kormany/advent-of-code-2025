package io.github.kormany.aoc2025.solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day05 {
    private static class Interval {
        long min;
        long max;

        Interval(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }

    private final List<Interval> intervals = new ArrayList<>();
    private final List<String> input;

    public Day05() {
        input = InputReader.readLines("day05.txt");

        int index = 0;
        while (index < input.size() && !input.get(index).isEmpty()) {
            String[] parts = input.get(index).split("-");
            intervals.add(new Interval(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
            index++;
        }
    }

    public int part1() {
        int count = 0;
        for (int i = intervals.size(); i < input.size(); i++) {
            String line = input.get(i);
            if (!line.isEmpty() && isFresh(Long.parseLong(line))) {
                count++;
            }
        }
        return count;
    }

    public long part2() {
        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingLong(i -> i.min));

        List<Interval> merged = new ArrayList<>();
        Interval current = sorted.getFirst();

        for (int i = 1; i < sorted.size(); i++) {
            Interval next = sorted.get(i);
            if (next.min <= current.max) {
                current.max = Math.max(current.max, next.max);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);

        return countIds(merged);
    }

    private boolean isFresh(long id) {
        for (Interval i : intervals) {
            if (id >= i.min && id <= i.max) return true;
        }
        return false;
    }

    private long countIds(List<Interval> mergedIntervals) {
        long count = 0;
        for (Interval interval : mergedIntervals) {
            count += interval.max - interval.min + 1;
        }
        return count;
    }
}
