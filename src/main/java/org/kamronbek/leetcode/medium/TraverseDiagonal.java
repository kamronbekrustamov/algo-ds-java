package org.kamronbek.leetcode.medium;


public class TraverseDiagonal {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new int[]{};
        int numOfRows = matrix.length;
        int numOfCols = matrix[0].length;
        int[] result = new int[numOfRows * numOfCols];
        int index = 0;
        for (int i = 0; i < numOfRows + numOfCols - 1; i++) {
            int leftRowIndex = i < numOfRows ? i : numOfRows - 1;
            int leftColIndex = i >= numOfRows ? i - numOfRows + 1: 0;
            int rightRowIndex = i >= numOfCols ? i - numOfCols + 1 : 0;
            int rightColIndex = i < numOfCols ? i : numOfCols - 1;

            while (rightColIndex >= leftColIndex && leftRowIndex >= rightRowIndex) {
                if ( i % 2 == 0) {
                    result[index] = matrix[leftRowIndex][leftColIndex];
                    leftColIndex++;
                    leftRowIndex--;
                } else {
                    result[index] = matrix[rightRowIndex][rightColIndex];
                    rightColIndex--;
                    rightRowIndex++;
                }
                index++;
            }
        }
        return result;
    }
}
