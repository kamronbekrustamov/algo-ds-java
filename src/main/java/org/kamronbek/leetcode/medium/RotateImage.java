package org.kamronbek.leetcode.medium;

public class RotateImage {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        int temp;
        for(int i = 0; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[length - 1 - j][length - 1 - i];
                matrix[length - 1 - j][length - 1 - i] = temp;
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length / 2; j++) {
                temp = matrix[j][i];
                matrix[j][i] = matrix[length - 1 - j][i];
                matrix[length - 1 - j][i] = temp;
            }
        }
    }
}