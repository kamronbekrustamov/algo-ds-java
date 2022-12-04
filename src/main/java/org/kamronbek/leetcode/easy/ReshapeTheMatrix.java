package org.kamronbek.leetcode.easy;

public class ReshapeTheMatrix {

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int rowSize = mat.length;
        int colSize = mat[0].length;
        if (rowSize * colSize == r * c) {
            int[][] newMatrix = new int[r][c];
            for(int i = 0; i < rowSize * colSize; i++) {
                newMatrix[i / c][i % c] = mat[i / colSize][i % colSize];
            }
            return newMatrix;
        } else {
            return mat;
        }
    }
}
