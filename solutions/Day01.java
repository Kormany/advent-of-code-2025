package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day01 {

    private static final int DIAL_SIZE = 100;
    private static final int START_POSITION = 50;
    private static final String INPUT_FILE = "inputs/day01.txt";

    public static int solvePart1() {
        int dialPosition = START_POSITION;
        int password = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                int distance = Integer.parseInt(line.substring(1));
                if (line.charAt(0) == 'L') {
                    distance *= -1;
                }
                dialPosition = (dialPosition + distance) % DIAL_SIZE;
                if (dialPosition == 0) {
                    password++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return password;
    }

    public static int solvePart2() {
        int dialPosition = START_POSITION;
        int password = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                int distance = Integer.parseInt(line.substring(1));
                for (int i = 0; i < distance; i++) {
                    if (line.charAt(0) == 'L') {
                        dialPosition = (dialPosition - 1) % DIAL_SIZE;
                    } else {
                        dialPosition = (dialPosition + 1) % DIAL_SIZE;
                    }
                    if (dialPosition == 0) {
                        password++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return password;
    }
}
