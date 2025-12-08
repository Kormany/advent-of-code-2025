package io.github.kormany.aoc2025.solutions;

import java.util.List;

public class Day01 {

    private static final int DIAL_SIZE = 100;
    private static final int START_POSITION = 50;

    private final List<String> input;

    public Day01() {
        input = InputReader.readLines("day01.txt");
    }

    public int part1() {
        int dialPosition = START_POSITION;
        int password = 0;

        for (String line : input) {
            int distance = Integer.parseInt(line.substring(1));
            if (line.charAt(0) == 'L') {
                distance = -distance;
            }
            dialPosition = (dialPosition + distance) % DIAL_SIZE;
            if (dialPosition < 0) dialPosition += DIAL_SIZE;

            if (dialPosition == 0) {
                password++;
            }
        }

        return password;
    }

    public int part2() {
        int dialPosition = START_POSITION;
        int password = 0;

        for (String line : input) {
            int distance = Integer.parseInt(line.substring(1));
            int direction = line.charAt(0) == 'L' ? -1 : 1;

            for (int i = 0; i < distance; i++) {
                dialPosition = (dialPosition + direction + DIAL_SIZE) % DIAL_SIZE;

                if (dialPosition == 0) {
                    password++;
                }
            }
        }

        return password;
    }
}