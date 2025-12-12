# Advent of Code 2025
This repository contains my solutions for [Advent of Code 2025](https://adventofcode.com/2025), implemented in **Java**.



## What is Advent of Code?
**Advent of Code** is an annual series of festive programming puzzles released daily in December.

In 2025, the event runs from **December 1 to 12**, delivering 23 algorithmic challenges.

---

## Project Structure

```
advent-of-code-2025/
├── src/
│   └── main/
│       ├── inputs/
│       │   ├── day01.txt
│       │   ├── day02.txt
│       │   ├── ...
│       │   └── day12.txt
│       │
│       └── java/
│           └── io/github/kormany/aoc2025/
│               ├── Main.java
│               └── solutions/
│                   ├── InputReader.java
│                   ├── Day01.java
│                   ├── Day02.java
│                   ├── ...
│                   └── Day12.java
│
├── pom.xml
├── .gitignore
└── README.md
```

* `Main.java` – runs all solutions sequentially
* `solutions/DayXX.java` – solution for the challenges of the given day
* `solutions/InputReader.java` – utility for reading puzzle input files
* `inputs/dayXX.txt` – puzzle input files
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

## 🔗 Links
* [Advent of Code 2025](https://adventofcode.com/2025) – Official website
