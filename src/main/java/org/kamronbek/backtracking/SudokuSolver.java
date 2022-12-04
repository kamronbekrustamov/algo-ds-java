package org.kamronbek.backtracking;


public class SudokuSolver {

    public static int[][] board = {
            {7,8,0,4,0,0,1,2,0},
            {6,0,0,0,7,5,0,0,9},
            {0,0,0,6,0,1,0,7,8},
            {0,0,7,0,4,0,2,6,0},
            {0,0,1,0,5,0,9,3,0},
            {9,0,4,0,6,0,0,0,5},
            {0,7,0,3,0,0,0,1,2},
            {1,2,0,0,0,7,4,0,0},
            {0,4,9,2,0,6,0,0,7}
    };

    public static void main(String[] args) {
        solve(0, 0);
        displayBoard();
    }

    // Displays the board
    public static void displayBoard() {
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    // Solves sudoku
    public static boolean solve(int row, int col) {
        int temp = nextEmptyCell(row);
        // Obtaining the first digit
        row = temp / 10;
        // Obtaining the second digit
        col = temp % 10;

        // If col is -1 then there is no cell to process
        if (col != -1) {
            for (int i = 1; i <= 9; i++) {
                if (isAcceptable(i, row, col)) {
                    board[row][col] = i;
                    if (solve(row, col)) {
                        return true;
                    }
                    board[row][col] = 0;
                }
            }
            return false;
        }
        return true;
    }

    // Checks if the passed value is acceptable at board[row][col]
    public static boolean isAcceptable(int value, int row, int col) {
        // Checking row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        // Checking column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }
        int r = row - row % 3;
        int c = col - col % 3;
        // Checking 3X3 square
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    // Returns next empty cell in sudoku as a two digit number
    // First digit means row and can be obtained with by dividing 10
    // Second digit means column and can be obtained % 10
    // If there is no cell numbers to return instead returns -1
    public static int nextEmptyCell(int row) {
        for (int i = row; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return i * 10 + j;
                }
            }
        }
        return -1;
    }

    // Checks if sudoku is solved
    public static boolean isSolved() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                int value = board[i][j];
                board[i][j] = 0;

                if (!isAcceptable(value, i, j)) {
                    return false;
                }
                board[i][j] = value;
            }
        }
        return true;
    }

}

