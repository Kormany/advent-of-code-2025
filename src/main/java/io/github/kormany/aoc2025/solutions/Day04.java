package io.github.kormany.aoc2025.solutions;

import java.util.List;
import java.util.ArrayList;

public class Day04 {
    private final List<String> input;

    public Day04() {
        input = InputReader.readLines("day04.txt");
    }

    public int part1() {
        int sum = 0;
        int rows = input.size();
        int cols = input.getFirst().length();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (input.get(i).charAt(j) == '@' && countAdjacentRolls(input, i, j) < 4) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public int part2() {
        List<String> board = new ArrayList<>(input);
        int sum = 0;
        int rows = board.size();
        int columns = board.getFirst().length();
        int removed;

        do {
            removed = 0;
            for (int i = 0; i < rows; i++) {
                char[] row = board.get(i).toCharArray();
                for (int j = 0; j < columns; j++) {
                    if (row[j] == '@' && countAdjacentRolls(board, i, j) < 4) {
                        row[j] = '.';
                        removed++;
                    }
                }
                board.set(i, new String(row));
            }
            sum += removed;
        } while (removed > 0);

        return sum;
    }

    private static int countAdjacentRolls(List<String> input, int x, int y) {
        int sumAdjacentRolls = 0;
        int rows = input.size();
        int columns = input.getFirst().length();
        if (!(x - 1 < 0 || y - 1 < 0) &&
                input.get(x - 1).charAt(y - 1) == '@') {
            sumAdjacentRolls++;
        }
        if (!(x - 1 < 0) &&
                input.get(x - 1).charAt(y) == '@') {
            sumAdjacentRolls++;
        }
        if (!(x - 1 < 0 || y + 1 >= columns) &&
                input.get(x - 1).charAt(y + 1) == '@') {
            sumAdjacentRolls++;
        }
        if (!(y - 1 < 0) &&
                input.get(x).charAt(y - 1) == '@') {
            sumAdjacentRolls++;
        }
        if (!(y + 1 >= columns) &&
                input.get(x).charAt(y + 1) == '@') {
            sumAdjacentRolls++;
        }
        if (!(x + 1 >= rows || y - 1 < 0) &&
                input.get(x + 1).charAt(y - 1) == '@') {
            sumAdjacentRolls++;
        }
        if (!(x + 1 >= rows) &&
                input.get(x + 1).charAt(y) == '@') {
            sumAdjacentRolls++;
        }
        if (!(x + 1 >= rows || y + 1 >= columns) &&
                input.get(x + 1).charAt(y + 1) == '@') {
            sumAdjacentRolls++;
        }
        return sumAdjacentRolls;
    }
}
