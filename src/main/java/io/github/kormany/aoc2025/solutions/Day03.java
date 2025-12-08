package io.github.kormany.aoc2025.solutions;

import java.util.List;

public class Day03 {
    private final List<String> input;

    public Day03() {
        input = InputReader.readLines("day03.txt");
    }

    public long part1() {
        long sum = 0;
        for (String line : input) {
            sum += maxTwoDigitValue(line);
        }
        return sum;
    }

    public long part2() {
        long sum = 0;
        for (String line : input) {
            sum += maxTwelveDigitValue(line);
        }
        return sum;
    }

    private int maxTwoDigitValue(String line) {
        int max = 0;
        for (int i = 0; i < line.length() - 1; i++) {
            int first = line.charAt(i) - '0';
            for (int j = i + 1; j < line.length(); j++) {
                int second = line.charAt(j) - '0';
                int value = first * 10 + second;
                if (value > max) max = value;
            }
        }
        return max;
    }

    private long maxTwelveDigitValue(String line) {
        StringBuilder sb = new StringBuilder(12);
        int lastIndex = -1;

        for (int i = 0; i < 12; i++) {
            char maxDigit = '0';
            for (int j = lastIndex + 1; j <= line.length() - 12 + i; j++) {
                if (line.charAt(j) > maxDigit) {
                    maxDigit = line.charAt(j);
                    lastIndex = j;
                }
            }
            sb.append(maxDigit);
        }

        return Long.parseLong(sb.toString());
    }
}
