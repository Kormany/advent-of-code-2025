package io.github.kormany.aoc2025.solutions;

import java.util.ArrayList;
import java.util.List;

public class Day06 {
    private final List<String> input;

    public Day06() {
        input = InputReader.readLines("day06.txt");
    }

    public long part1() {
        List<String[]> problems = new ArrayList<>();
        for (String line : input) {
            problems.add(line.trim().replaceAll(" +", " ").split(" "));
        }
        problems = rotateLeft(problems);
        long sumAnswers = 0;
        for (String[] problem : problems) {
            sumAnswers += solveProblem(problem);
        }
        return sumAnswers;
    }

    public long part2() {
        char[][] problems = new char[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                problems[i][j] = input.get(i).charAt(j);
            }
        }
        problems = rotateLeft(problems);
        long sumAnswers = 0;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> problem = new ArrayList<>();
        for (char[] chars : problems) {
            sb.delete(0, sb.length());
            for (int j = 0; j < chars.length; j++) {
                if (j == chars.length - 1) {
                    if (chars[j] == '*' || chars[j] == '+') {
                        problem.add(sb.toString());
                        sb.delete(0, sb.length());
                        sb.append(chars[j]);
                        problem.add(sb.toString());
                        sumAnswers += solveProblem(problem);
                        problem.clear();
                    } else {
                        if (chars[j] != ' ') {
                            sb.append(chars[j]);
                        }
                        problem.add(sb.toString());
                    }
                } else if (chars[j] != ' ') {
                    sb.append(chars[j]);
                }
            }

        }
        return sumAnswers;
    }

    public static List<String[]> rotateLeft(List<String[]> matrix) {
        if (matrix == null || matrix.isEmpty()) {
            return new ArrayList<>();
        }

        int rowCount = matrix.size();
        int colCount = matrix.getFirst().length;

        List<String[]> rotated = new ArrayList<>(colCount);

        for (int col = colCount - 1; col >= 0; col--) {

            String[] newRow = new String[rowCount];

            for (int row = 0; row < rowCount; row++) {
                newRow[row] = matrix.get(row)[col];
            }

            rotated.add(newRow);
        }

        return rotated;
    }

    public static char[][] rotateLeft(char[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        char[][] rotated = new char[columns][rows];

        for (int c = columns - 1, newRow = 0; c >= 0; c--, newRow++) {
            for (int r = 0; r < rows; r++) {
                rotated[newRow][r] = matrix[r][c];
            }
        }

        return rotated;
    }

    private static long solveProblem(String[] problem) {
        long result = 0;
        if (problem[problem.length - 1].equals("*")) {
            result = 1;
            for (int i = 0; i < problem.length - 1; i++) {
                result *= Integer.parseInt(problem[i]);
            }
        } else if (problem[problem.length - 1].equals("+")) {
            for (int i = 0; i < problem.length - 1; i++) {
                result += Integer.parseInt(problem[i]);
            }
        }
        return result;
    }

    private static long solveProblem(List<String> problem) {
        long result = 0;
        int n = problem.size();

        String operator = problem.get(n - 1);

        if (operator.equals("*")) {
            result = 1;
            for (int i = 0; i < n - 1; i++) {
                if (problem.get(i).isEmpty()) {
                    continue;
                }
                result *= Integer.parseInt(problem.get(i));
            }
        } else if (operator.equals("+")) {
            for (int i = 0; i < n - 1; i++) {
                if (problem.get(i).isEmpty()) {
                    continue;
                }
                result += Integer.parseInt(problem.get(i));
            }
        }

        return result;
    }
}
