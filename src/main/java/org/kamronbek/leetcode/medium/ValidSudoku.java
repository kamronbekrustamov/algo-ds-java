package org.kamronbek.leetcode.medium;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            boolean[] rows = new boolean[9];
            boolean[] cols = new boolean[9];
            boolean[] subs = new boolean[9];
            int subRow = (i / 3) * 3;
            int subCol = (i % 3) * 3;
            for (int j = 0; j < 9; j++) {
                // check by row
                if (board[i][j] != '.') {
                    int num = board[i][j] - 48;
                    if (rows[num - 1])
                        return false;
                    rows[num - 1] = true;
                }
                
                // check by column
                if (board[j][i] != '.') {
                    int num = board[j][i] - 48;
                    if (cols[num - 1])
                        return false;
                    cols[num - 1] = true;
                }

                int rowIndex = subRow + j / 3;
                int colIndex = subCol + j % 3; 
                if (board[rowIndex][colIndex] != '.') {
                    int num = board[rowIndex][colIndex] - 48;
                    if (subs[num - 1])
                        return false;
                    subs[num - 1] = true;
                }
            }
        }
        return true;
    }
}