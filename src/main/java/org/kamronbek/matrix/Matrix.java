package org.kamronbek.matrix;

import org.kamronbek.algo.number.Fraction;

import java.util.*;

public class Matrix {
    private final int rows;
    private final int columns;
    private final Fraction[][] matrixArray;

    private Matrix(Fraction[][] matrixArray) {
        if (!isValidMatrix(matrixArray)) {
            throw new IllegalArgumentException("Matrix is not valid.");
        }
        this.matrixArray = matrixArray;
        this.rows = matrixArray.length;
        this.columns = matrixArray[0].length;
    }

    public static Matrix of(Fraction[][] matrixArray) {
        return new Matrix(matrixArray);
    }

    private boolean isValidMatrix(Fraction[][] matrixArray) {
        // Checking the matrix is not null or empty
        if (matrixArray == null || matrixArray.length == 0 || matrixArray[0] == null) {
            return false;
        }
        int columnsCount = matrixArray[0].length;
        if (columnsCount == 0)
            return false;
        // Checking the number of columns for all rows is the same
        for (Fraction[] cells : matrixArray) {
            if (cells == null || columnsCount != cells.length) {
                return false;
            } else {
                for (Fraction cell: cells) {
                    if (cell == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Matrix add(Matrix matrix) {
        if (rows != matrix.rows || columns != matrix.columns) {
            throw new IllegalArgumentException("Only matrices with the same dimensions can be added");
        }
        Fraction[][] additionMatrixArray = new Fraction[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                additionMatrixArray[i][j] = this.matrixArray[i][j].add(matrix.matrixArray[i][j]);
            }
        }
        return of(additionMatrixArray);
    }

    public Matrix subtract(Matrix matrix) {
        if (rows != matrix.rows || columns != matrix.columns) {
            throw new IllegalArgumentException("Only matrices with the same dimensions can be added");
        }
        Fraction[][] additionMatrixArray = new Fraction[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                additionMatrixArray[i][j] = this.matrixArray[i][j].subtract(matrix.matrixArray[i][j]);
            }
        }
        return of(additionMatrixArray);
    }

    public Matrix multiply(Matrix matrix) {
        if (columns != matrix.rows) {
            throw new IllegalArgumentException("Matrices must have compatible dimensions(this.columns = matrix.rows)");
        }
        Fraction[][] multiplicationMatrixArray = new Fraction[rows][matrix.columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                Fraction fraction = Fraction.ZERO;
                for (int k = 0; k < columns; k++) {
                    fraction = fraction.add(this.matrixArray[i][k].multiply(matrix.matrixArray[k][j]));
                }
                multiplicationMatrixArray[i][j] = fraction;
            }
        }
        return of(multiplicationMatrixArray);
    }

    public boolean isIdentityMatrix() {
        if (rows != columns) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    if (!matrixArray[i][j].isOne()) {
                        return false;
                    }
                } else if (!matrixArray[i][j].isZero()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSquareMatrix() {
        return rows == columns;
    }

    @Override
    public String toString() {
        List<Integer> columnWiseWidth = new ArrayList<>(columns);
        for (int col = 0; col < columns; col++) {
            int max = 0;
            for (int row = 0; row < rows; row++) {
                int length = matrixArray[row][col].toString().length();
                if (max < length) {
                    max = length;
                }
            }
            columnWiseWidth.add(max);
        }
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            result.append("[");
            for (int col = 0; col < columns; col++) {
                String val = matrixArray[row][col].toString();
                result.append(val).append(" ".repeat(columnWiseWidth.get(col) - val.length()));
                if (col != columns - 1) {
                    result.append("  ");
                }
            }
            result.append("]");
            if (row != rows - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
