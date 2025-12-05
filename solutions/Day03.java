package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day03 {

    private static final String PATH = "inputs/day03.txt";

    public static int part1() {
        int sumJoltage = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                int maxJoltage = 0;
                for (int i = 0; i < line.length() - 1; i++) {
                    for (int j = i + 1; j < line.length(); j++) {
                        if (calculateJoltage(line.charAt(i), line.charAt(j))
                                > maxJoltage) {
                            maxJoltage = calculateJoltage(line.charAt(i),
                                    line.charAt(j));
                        }
                    }
                }
                sumJoltage += maxJoltage;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return sumJoltage;
    }

    public static long part2() {
        StringBuilder sb = new StringBuilder(12);
        long sumJoltage = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                int lastUsedDigitIndex = -1;
                for (int i = 0; i < 12; i++) {
                    char maxDigit = '0';
                    for (int j = lastUsedDigitIndex + 1;
                            j <= line.length() - 12 + i; j++) {
                        if (line.charAt(j) > maxDigit) {
                            maxDigit = line.charAt(j);
                            lastUsedDigitIndex = j;
                        }
                    }
                    sb.append(maxDigit);
                }
                sumJoltage += Long.parseLong(sb.toString());
                sb.delete(0, 12);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return sumJoltage;
    }

    public static int calculateJoltage(char firstDigit, char secondDigit) {
        return (firstDigit - '0') * 10 + secondDigit - '0';
    }
}
