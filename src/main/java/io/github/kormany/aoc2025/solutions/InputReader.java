package io.github.kormany.aoc2025.solutions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class InputReader {

    public static List<String> readLines(String filename) {
        try {
            Path path = Paths.get("src/main/resources/inputs/" + filename);
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
            return List.of();
        }
    }
}

