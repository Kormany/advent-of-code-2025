package io.github.kormany.aoc2025.solutions;

import java.util.List;

public class Day02 {
    private final List<String> input;

    public Day02() {
        input = InputReader.readLines("day02.txt");
    }

    public long part1() {
        long sumInvalidIDs = 0;

        for (String line : input) {
            String[] ranges = line.split(",");
            for (String range : ranges) {
                long[] bounds = parseRange(range);
                for (long i = bounds[0]; i <= bounds[1]; i++) {
                    if (hasEqualHalves(i)) {
                        sumInvalidIDs += i;
                    }
                }
            }
        }

        return sumInvalidIDs;
    }

    public long part2() {
        long sumInvalidIDs = 0;

        for (String line : input) {
            String[] ranges = line.split(",");
            for (String range : ranges) {
                long[] bounds = parseRange(range);
                for (long i = bounds[0]; i <= bounds[1]; i++) {
                    String digits = String.valueOf(i);
                    if (isInvalidID(digits)) {
                        sumInvalidIDs += i;
                    }
                }
            }
        }

        return sumInvalidIDs;
    }

    private long[] parseRange(String range) {
        String[] parts = range.split("-");
        return new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])};
    }

    private boolean hasEqualHalves(long n) {
        long copy = n;
        int len = 0;
        while (copy > 0) {
            len++;
            copy /= 10;
        }
        if (len % 2 != 0) return false;

        long divisor = 1;
        for (int i = 0; i < len / 2; i++) divisor *= 10;

        long firstHalf = n / divisor;
        long secondHalf = n % divisor;

        return firstHalf == secondHalf;
    }

    private boolean isInvalidID(String s) {
        int len = s.length();

        for (int size = 1; size <= len / 2; size++) {
            if (len % size == 0) {
                boolean invalid = true;
                for (int i = size; i < len; i += size) {
                    for (int j = 0; j < size; j++) {
                        if (s.charAt(j) != s.charAt(i + j)) {
                            invalid = false;
                            break;
                        }
                    }
                    if (!invalid) break;
                }
                if (invalid) return true;
            }
        }
        return false;
    }
}
