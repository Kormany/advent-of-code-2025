# Advent of Code 2025
This repository contains my solutions for [Advent of Code 2025](https://adventofcode.com/2025), implemented in **Java**.

## About Advent of Code
**Advent of Code** is an annual programming challenge with daily puzzles released throughout December.
In 2025, the challenge runs from **December 1 to 12**, with a total of **23** algorithmic puzzles.

---

## Project Structure
```
advent-of-code-2025/
├── src/main/
│   ├── resources/inputs/
│   │   ├── day01.txt
│   │   ├── day02.txt
│   │   ├── ...
│   │   └── day12.txt
│   │
│   └── java/io/github/kormany/aoc2025/
│       ├── Main.java
│       └── solutions/
│           ├── InputReader.java
│           ├── Day01.java
│           ├── Day02.java
│           ├── ...
│           └── Day12.java
│
├── pom.xml
├── .gitignore
└── README.md
```
* `Main.java` – entry point that executes all daily solutions
* `solutions/DayXX.java` – implementation for a specific day’s puzzles
* `solutions/InputReader.java` – helper for loading inputs
* `inputs/dayXX.txt` – puzzle input data
---

## Goals
### Main Goal
Solve all Advent of Code 2025 puzzles in Java. ✔
### Stretch Goal
Finish every solution by December 12. ✔

---

## How to Run
1. **Compile:**
```bash
mvn compile
```
2. **Run:**
```bash
mvn exec:java '-Dexec.mainClass=io.github.kormany.aoc2025.Main'
```

---

## Links
* [Advent of Code 2025](https://adventofcode.com/2025) – Official website
