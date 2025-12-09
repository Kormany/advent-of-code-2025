package io.github.kormany.aoc2025.solutions;

import java.util.List;

public class Day07 {
    private final List<String> input;

    public Day07() {
        input = InputReader.readLines("day07.txt");
    }

    public int part1() {
        char[][] diagram = getDiagram();
        return countSplits(diagram);
    }

    public long part2() {
        char[][] diagram = getDiagram();
        return countTimelines(diagram);
    }

    private char[][] getDiagram() {
        char[][] diagram = new char[input.size()][input.getFirst().length()];
        for (int i = 0; i < diagram.length; i++) {
            for (int j = 0; j < diagram[0].length; j++) {
                diagram[i][j] = input.get(i).charAt(j);
            }
        }
        return diagram;
    }

    private int countSplits(char[][] diagram) {
        int splits = 0;
        for (int i = 0; i < diagram.length - 1; i++) {
            for (int j = 0; j < diagram[0].length; j++) {
                if (diagram[i][j] == '|' || diagram[i][j] == 'S') {
                    if (diagram[i + 1][j] == '^') {
                        splits++;
                        if (j - 1 >= 0) diagram[i + 1][j - 1] = '|';
                        if (j + 1 < diagram[0].length) diagram[i + 1][j + 1] = '|';
                    } else {
                        diagram[i + 1][j] = '|';
                    }
                }
            }
        }
        return splits;
    }

    private long countTimelines(char[][] diagram) {
        int startRow = -1, startCol = -1;

        for (int r = 0; r < diagram.length; r++) {
            for (int c = 0; c < diagram[0].length; c++) {
                if (diagram[r][c] == 'S') {
                    startRow = r;
                    startCol = c;
                }
            }
        }

        Long[][] memo = new Long[diagram.length][diagram[0].length];

        return dfs(startRow + 1, startCol, diagram, memo);
    }

    private long dfs(int r, int c, char[][] d, Long[][] memo) {
        int rows = d.length;
        int cols = d[0].length;

        if (r >= rows || c < 0 || c >= cols) {
            return 1;
        }

        if (memo[r][c] != null) {
            return memo[r][c];
        }

        long result;

        if (d[r][c] == '^') {
            long left = dfs(r + 1, c - 1, d, memo);
            long right = dfs(r + 1, c + 1, d, memo);
            result = left + right;
        } else {
            result = dfs(r + 1, c, d, memo);
        }

        memo[r][c] = result;
        return result;
    }
}
