package org.kamronbek.backtracking;


import java.util.Scanner;

public class NQueens {
    // 1D array to store occupied (row, column) pairs.
    // If board[row] == -1, that row is not occupied. Else it stores the column number
    public static int[] board;
    // Size of the board
    public static int size;

    public static void main(String[] args) {
        // Getting the size of the board from the user
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the size of the board: ");
            size = scanner.nextInt();
        }

        // If size is less than 4, the problem is not solvable
        if (size < 4) {
            System.out.println("The problem is not solvable for the size you gave");
        } else {
            // Initializing the board with -1 values
            initialize();
            // Solving the problem
            solve(0);
            // Display the result
            displayBoard();
        }
    }

    // Initializing the board and its values
    public static void initialize() {
        board = new int[size];
        for (int i = 0; i < size; i++)
            board[i] = -1;
    }

    public static boolean solve(int col) {
        if (col == size)
            return true;
        for(int i = 0; i < size; i++) {
            if (isAcceptable(i, col)) {
                board[i] = col;
                if (solve(col + 1))
                    return true;
                board[i] = -1;
            }
        }

        return false;
    }

    // Returns whether (row, col) is acceptable in current board or not
    public static boolean isAcceptable(int row, int col) {
        // Checking if the row is occupied
        if (board[row] != -1)
            return false;

        // Checking if diagonal attack exists
        for (int i = 0; i < size; i++) {
            if (board[i] == -1)
                continue;
            if (Math.abs(row - i) == Math.abs(col - board[i]))
                return false;
        }

        return true;
    }

    // Used to display the board and correct positions of queens
    public static void displayBoard() {
        printDashes();
        for (int i = 0; i < size; i++) {
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                if (j == board[i])
                    System.out.print(" " + 1 + " |");
                else
                    System.out.print(" " + 0 + " |");
            }
            printDashes();
        }
    }

    // Helps to displayBoard()
    public static void printDashes() {
        System.out.print("\n ");
        for(int i = 0; i < board.length; i++)
            System.out.print("--- ");
        System.out.println();
    }
}
