package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day02 {
    private static final String PATH = "inputs/day02.txt";

    public static long part1() {
        long sumInvalidIDs = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ranges = line.split(",");
                for (String range : ranges) {
                    long min = Long.parseLong(range.split("-")[0]);
                    long max = Long.parseLong(range.split("-")[1]);
                    for (long i = min; i <= max; i++) {
                        String digits = String.valueOf(i);
                        if (digits.length() % 2 == 0) {
                            if (digits.substring(0, digits.length() / 2).equals
                            (digits.substring(digits.length() / 2))) {
                                sumInvalidIDs += i;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return sumInvalidIDs;
    }

    public static long part2() {
        long sumInvalidIDs = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ranges = line.split(",");
                for (String range : ranges) {
                    long min = Long.parseLong(range.split("-")[0]);
                    long max = Long.parseLong(range.split("-")[1]);
                    for (long i = min; i <= max; i++) {
                        String digits = String.valueOf(i);
                        boolean isInvalidID = false;
                        for (int j = 1; j <= digits.length() / 2; j++) {
                            if (digits.length() % j == 0) {
                                List<String> parts = splitBySize(digits, j);
                                if (allElementsEqual(parts)) {
                                    isInvalidID = true;
                                }
                            }
                        }
                        if (isInvalidID) {
                            sumInvalidIDs += i;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return sumInvalidIDs;
    }

    public static List<String> splitBySize(String text, int size) {
        List<String> parts = new ArrayList<>();

        for (int i = 0; i < text.length(); i += size) {
            int end = Math.min(i + size, text.length());
            parts.add(text.substring(i, end));
        }

        return parts;
    }

    public static boolean allElementsEqual(List<String> list) {
        if (list.isEmpty()) {
            return false;
        }

        String first = list.get(0);
        for (String item : list) {
            if (!item.equals(first)) {
                return false;
            }
        }
        return true;
    }
}
