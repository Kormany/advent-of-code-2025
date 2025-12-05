package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day04 {
    private static final String PATH = "inputs/day04.txt";

    public static int part1() {
        int sumAccessibleRolls = 0;
        ArrayList<String> input = readInput();
        int rows = input.size();
        int columns = input.get(0).length();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (input.get(i).charAt(j) == '@' && 
                    countAdjacentRolls(input, rows, columns, i, j) < 4) {
                    sumAccessibleRolls++;
                }
            }
        }
        return sumAccessibleRolls;
    }

    public static int part2() {
        int sumAccessibleRolls = 0;
        int removedRolls;
        ArrayList<String> input = readInput();
        int rows = input.size();
        int columns = input.get(0).length();
        do { 
            removedRolls = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (input.get(i).charAt(j) == '@' && 
                        countAdjacentRolls(input, rows, columns, i, j) < 4) {
                        char[] arr = input.get(i).toCharArray();
                        arr[j] = '.';
                        input.set(i, new String(arr));
                        removedRolls++;
                    }
                }
            }
            sumAccessibleRolls += removedRolls;
        } while (removedRolls > 0);

        return sumAccessibleRolls;
    }

    private static ArrayList<String> readInput() {
        ArrayList<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return input;
    }

    private static int countAdjacentRolls(
        ArrayList<String> input, int rows, int columns, int x, int y) {
        int sumAdjacentRolls = 0;
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
