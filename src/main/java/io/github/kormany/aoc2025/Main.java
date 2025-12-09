package io.github.kormany.aoc2025;

import io.github.kormany.aoc2025.solutions.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Object[] days = {
                new Day01(),
                new Day02(),
                new Day03(),
                new Day04(),
                new Day05(),
                new Day06(),
                new Day07(),
//                new Day08(),
//                new Day09(),
//                new Day10(),
//                new Day11(),
//                new Day12()
        };

        for (int i = 0; i < days.length; i++) {
            int dayNumber = i + 1;
            Object day = days[i];

            System.out.println("\nDay " + dayNumber + ":");

            long start1 = System.nanoTime();
            Object result1 = day.getClass().getMethod("part1").invoke(day);
            long end1 = System.nanoTime();
            System.out.printf("> Part 1: %s (%.3f ms)%n", result1, (end1 - start1) / 1_000_000.0);

            long start2 = System.nanoTime();
            Object result2 = day.getClass().getMethod("part2").invoke(day);
            long end2 = System.nanoTime();
            System.out.printf("> Part 2: %s (%.3f ms)%n", result2, (end2 - start2) / 1_000_000.0);
        }
    }
}