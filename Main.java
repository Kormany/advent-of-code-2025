import solutions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Day 1:");
        long start = System.currentTimeMillis();
        System.out.println("> Part 1: " + Day01.part1());
        System.out.println("> Part 2: " + Day01.part2());
        long end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + " ms\n");

        System.out.println("Day 2:");
        start = System.currentTimeMillis();
        System.out.println("> Part 1: " + Day02.part1());
        System.out.println("> Part 2: " + Day02.part2());
        end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + " ms\n");

        System.out.println("Day 3:");
        start = System.currentTimeMillis();
        System.out.println("> Part 1: " + Day03.part1());
        System.out.println("> Part 2: " + Day03.part2());
        end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + " ms\n");

        System.out.println("Day 4:");
        start = System.currentTimeMillis();
        System.out.println("> Part 1: " + Day04.part1());
        System.out.println("> Part 2: " + Day04.part2());
        end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + " ms\n");
    }
}