package io.github.kormany.aoc2025.solutions;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day09 {
    private final List<String> input;

    public Day09() {
        input = InputReader.readLines("day09.txt");
    }

    public long part1() {
        return findLargestArea(getCorners());
    }

    public long part2() {
        int[][] corners = getCorners();
        Polygon poly = new Polygon(corners);

        long largest = 0;

        for (int i = 0; i < corners.length; i++) {
            for (int j = i + 1; j < corners.length; j++) {

                int x1 = corners[i][0], y1 = corners[i][1];
                int x2 = corners[j][0], y2 = corners[j][1];

                int lx = min(x1, x2);
                int hx = max(x1, x2);
                int ly = min(y1, y2);
                int hy = max(y1, y2);

                if (!poly.isInsideOrOn(lx, ly)) continue;
                if (!poly.isInsideOrOn(lx, hy)) continue;
                if (!poly.isInsideOrOn(hx, ly)) continue;
                if (!poly.isInsideOrOn(hx, hy)) continue;

                if (!poly.rectangleFullyInside(lx, ly, hx, hy)) continue;

                long area = (long) (hx - lx + 1) * (hy - ly + 1);
                if (area > largest) largest = area;
            }
        }

        return largest;
    }

    private int[][] getCorners() {
        int n = input.size();
        int[][] corners = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] parts = input.get(i).split(",");
            corners[i][0] = Integer.parseInt(parts[0]);
            corners[i][1] = Integer.parseInt(parts[1]);
        }
        return corners;
    }

    private long findLargestArea(int[][] corners) {
        long best = 0;
        for (int i = 0; i < corners.length - 1; i++) {
            for (int j = i + 1; j < corners.length; j++) {
                long area = (long) (abs(corners[i][0] - corners[j][0]) + 1)
                        * (abs(corners[i][1] - corners[j][1]) + 1);
                if (area > best) best = area;
            }
        }
        return best;
    }

    private record Polygon(List<int[]> pts) {

            private Polygon(int[][] pts) {
                this(new ArrayList<>(pts.length));
                for (int[] c : pts) {
                    this.pts.add(new int[]{c[0], c[1]});
                }
            }

            public boolean isInsideOrOn(int x, int y) {
                for (int i = 0; i < pts.size(); i++) {
                    int[] a = pts.get(i);
                    int[] b = pts.get((i + 1) % pts.size());
                    if (onSegment(x, y, a[0], a[1], b[0], b[1])) return true;
                }

                int crossings = 0;
                for (int i = 0; i < pts.size(); i++) {
                    int[] p1 = pts.get(i);
                    int[] p2 = pts.get((i + 1) % pts.size());

                    int y1 = p1[1], y2 = p2[1];
                    int x1 = p1[0], x2 = p2[0];

                    boolean hit = (y1 <= y && y2 > y) || (y2 <= y && y1 > y);
                    if (hit) {
                        double xInt = x1 + (double) (y - y1) * (x2 - x1) / (double) (y2 - y1);
                        if (x < xInt) crossings++;
                    }
                }

                return (crossings & 1) == 1;
            }

            public boolean rectangleFullyInside(int lx, int ly, int hx, int hy) {
                for (int[] p : pts) {
                    if (p[0] > lx && p[0] < hx && p[1] > ly && p[1] < hy) {
                        return false;
                    }
                }

                for (int i = 0; i < pts.size(); i++) {
                    int[] a = pts.get(i);
                    int[] b = pts.get((i + 1) % pts.size());

                    int x1 = a[0], y1 = a[1];
                    int x2 = b[0], y2 = b[1];

                    if (x1 == x2) {
                        int yA = min(y1, y2);
                        int yB = max(y1, y2);

                        if (x1 > lx && x1 < hx) {
                            int oy1 = max(yA, ly);
                            int oy2 = min(yB, hy);
                            if (oy2 > oy1) return false;
                        }

                    } else if (y1 == y2) {
                        int xA = min(x1, x2);
                        int xB = max(x1, x2);

                        if (y1 > ly && y1 < hy) {
                            int ox1 = max(xA, lx);
                            int ox2 = min(xB, hx);
                            if (ox2 > ox1) return false;
                        }

                    } else {
                        if (segmentIntersectsRect(x1, y1, x2, y2, lx, ly, hx, hy)) {
                            return false;
                        }
                    }
                }

                return true;
            }

            private boolean onSegment(int px, int py, int x1, int y1, int x2, int y2) {
                long cross = (long) (px - x1) * (y2 - y1) - (long) (py - y1) * (x2 - x1);
                if (cross != 0) return false;
                return px >= min(x1, x2) && px <= max(x1, x2)
                        && py >= min(y1, y2) && py <= max(y1, y2);
            }

            private boolean segmentIntersectsRect(int x1, int y1, int x2, int y2,
                                                  int lx, int ly, int hx, int hy) {
                if (inside(x1, y1, lx, ly, hx, hy)) return true;
                if (inside(x2, y2, lx, ly, hx, hy)) return true;

                return segments(x1, y1, x2, y2, lx, ly, hx, ly)
                        || segments(x1, y1, x2, y2, hx, ly, hx, hy)
                        || segments(x1, y1, x2, y2, hx, hy, lx, hy)
                        || segments(x1, y1, x2, y2, lx, hy, lx, ly);
            }

            private boolean inside(int x, int y, int lx, int ly, int hx, int hy) {
                return x > lx && x < hx && y > ly && y < hy;
            }

            private boolean segments(int x1, int y1, int x2, int y2,
                                 int x3, int y3, int x4, int y4) {
                long d1 = orient(x3, y3, x4, y4, x1, y1);
                long d2 = orient(x3, y3, x4, y4, x2, y2);
                long d3 = orient(x1, y1, x2, y2, x3, y3);
                long d4 = orient(x1, y1, x2, y2, x4, y4);

                if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
                        ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0)))
                    return true;

                if (d1 == 0 && onSegment(x1, y1, x3, y3, x4, y4)) return true;
                if (d2 == 0 && onSegment(x2, y2, x3, y3, x4, y4)) return true;
                if (d3 == 0 && onSegment(x3, y3, x1, y1, x2, y2)) return true;
                return d4 == 0 && onSegment(x4, y4, x1, y1, x2, y2);
            }

            private long orient(int ax, int ay, int bx, int by, int cx, int cy) {
                return (long) (bx - ax) * (cy - ay) - (long) (by - ay) * (cx - ax);
            }
        }
}
