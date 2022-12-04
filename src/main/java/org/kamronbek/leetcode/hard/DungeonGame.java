package org.kamronbek.leetcode.hard;

public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon[0].length == 0)
            return 1;

        final int row = dungeon.length - 1; // the index of last row
        final int col = dungeon[0].length - 1; // the index of last column

        int[][] dp = new int[row + 1][col + 1];

        dp[row][col] = 1 - Math.min(dungeon[row][col], 0);

        for (int i = row - 1; i >= 0; i--)
            dp[i][col] = Math.max(dp[i + 1][col] - dungeon[i][col], 1);

        for (int j = col - 1; j >= 0; j--)
            dp[row][j] = Math.max(dp[row][j + 1] - dungeon[row][j], 1);

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                int right = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                int down = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }
}
