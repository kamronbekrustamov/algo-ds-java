package org.kamronbek.leetcode.easy;

public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        if (grid.length == 0) return 0;
        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 < 0 || grid[i - 1][j] == 0)
                        perimeter++;
                    if (j - 1 < 0 || grid[i][j - 1] == 0)
                        perimeter++;
                    if (i + 1 >= rows || grid[i + 1][j] == 0)
                        perimeter++;
                    if (j + 1 >= cols || grid[i][j + 1] == 0)
                        perimeter++;
                }
            }
        }
        return perimeter;
    }

}
