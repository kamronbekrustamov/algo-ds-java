package org.kamronbek.leetcode.easy;

public class MaxAreaOfIsland {
    private int[][] grid;
    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
        int max = 0;

        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                max = Math.max(max, returnMax(i, j));
            }
        }
        return max;
    }

    public int returnMax(int i, int j) {
        if (j < 0 || i < 0 || i >= grid.length || j >= grid[i].length || visited[i][j] || grid[i][j] == 0)
            return 0;

        visited[i][j] = true;
        return 1 + returnMax(i, j - 1) + returnMax(i - 1, j) + returnMax(i, j + 1) + returnMax(i + 1, j);
    }

}