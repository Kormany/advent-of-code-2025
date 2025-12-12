package io.github.kormany.aoc2025.solutions;

import com.microsoft.z3.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class Day10 {
    private static class Machine {
        int[] lights;
        int[][] buttons;
        int[] requirements;

        public Machine(String input) {
            String[] parts = input.split(" ");
            lights = new int[parts[0].length() - 2];
            for (int i = 0; i < parts[0].length() - 2; i++) {
                lights[i] = parts[0].charAt(i + 1) == '.' ? 0 : 1;
            }
            buttons = new int[parts.length - 2][];
            for (int i = 0; i < buttons.length; i++) {
                String inside = parts[i + 1].substring(1, parts[i + 1].length() - 1);
                if (inside.isEmpty()) {
                    buttons[i] = new int[0];
                    continue;
                }
                String[] buttonParts = inside.split(",");
                buttons[i] = new int[buttonParts.length];
                for (int j = 0; j < buttonParts.length; j++) {
                    buttons[i][j] = Integer.parseInt(buttonParts[j]);
                }
            }
            String[] requirementParts =
                    parts[parts.length - 1]
                            .substring(1, parts[parts.length - 1].length() - 1)
                            .split(",");
            requirements = new int[requirementParts.length];
            for (int i = 0; i < requirementParts.length; i++) {
                requirements[i] = Integer.parseInt(requirementParts[i]);
            }
        }
    }

    private final List<String> input;

    public Day10() {
        input = InputReader.readLines("day10.txt");
    }

    public int part1() {
        ArrayList<Machine> machines = new ArrayList<>();
        for (String line : input) {
            machines.add(new Machine(line));
        }
        int sumButtonPresses = 0;
        for (Machine machine : machines) {
            sumButtonPresses += generateAllCombinations(machine.buttons, machine.lights);
        }
        return sumButtonPresses;
    }

    public int part2() {
        ArrayList<Machine> machines = new ArrayList<>();
        for (String line : input) {
            machines.add(new Machine(line));
        }
        int sumButtonPresses = 0;
        for (Machine m : machines) {
            sumButtonPresses += solveMachineWithZ3(m.buttons, m.requirements);
        }
        return sumButtonPresses;
    }

    private int solveMachineWithZ3(int[][] buttons, int[] req) {
        HashMap<String, String> cfg = new HashMap<>();
        cfg.put("model", "true");
        try (Context ctx = new Context(cfg)) {
            Optimize opt = ctx.mkOptimize();
            int numButtons = buttons.length;
            int numCounters = req.length;
            IntExpr[] press = new IntExpr[numButtons];

            for (int i = 0; i < numButtons; i++) {
                press[i] = ctx.mkIntConst("press_" + i);
                opt.Add(ctx.mkGe(press[i], ctx.mkInt(0)));
            }

            for (int c = 0; c < numCounters; c++) {
                ArithExpr<IntSort> sum = ctx.mkInt(0);
                for (int b = 0; b < numButtons; b++) {
                    int finalC = c;
                    if (Arrays.stream(buttons[b]).anyMatch(idx -> idx == finalC)) {
                        sum = ctx.mkAdd(sum, press[b]);
                    }
                }
                opt.Add(ctx.mkEq(sum, ctx.mkInt(req[c])));
            }

            ArithExpr<IntSort> total = ctx.mkInt(0);
            for (IntExpr p : press) {
                total = ctx.mkAdd(total, p);
            }
            opt.MkMinimize(total);

            if (opt.Check() != Status.SATISFIABLE) {
                return -1;
            }

            Model model = opt.getModel();
            int totalPresses = 0;
            for (IntExpr p : press) {
                totalPresses += Integer.parseInt(model.eval(p, true).toString());
            }

            return totalPresses;
        } catch (Z3Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int generateAllCombinations(int[][] buttons, int[] target) {
        int n = buttons.length;
        for (int r = 1; r <= n; r++) {
            if (generate(buttons, r, 0, new ArrayList<>(), target)) {
                return r;
            }
        }
        return 0;
    }

    private static boolean generate(int[][] buttons, int r, int start,
                                    List<int[]> current, int[] target) {
        if (current.size() == r) {
            int[] state = new int[target.length];
            for (int[] button : current) {
                for (int idx : button) {
                    state[idx] ^= 1;
                }
            }
            return Arrays.equals(state, target);
        }

        for (int i = start; i < buttons.length; i++) {
            current.add(buttons[i]);
            if (generate(buttons, r, i + 1, current, target)) {
                return true;
            }
            current.removeLast();
        }

        return false;
    }
}
