package org.kamronbek.leetcode.easy;

public class FlipImage {
    public int[][] flipAndInvertImage(int[][] A) {
        int numOfRows = A.length;
        int numOfCols = A[0].length;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols / 2; j++) {
            int temp = A[i][j];
            A[i][j] = A[i][numOfCols - j - 1];
            A[i][numOfCols - j - 1] = temp;
            }
        }

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                if (A[i][j] == 0)
                    A[i][j] = 1;
                else
                    A[i][j] = 0;
            }
        }

        return A;
    }
}
