package org.kamronbek.matrix;

import org.kamronbek.algo.number.Fraction;

import java.util.Arrays;

public class Matrices {
    private Matrices() {}

    public static Matrix of(String matrixString) {
        if (matrixString == null || matrixString.isBlank()) {
            throw new IllegalArgumentException("MatrixString cannot be null or blank");
        }
        try {
            Fraction[][] matrixArray = Arrays.stream(matrixString.trim().split("\n"))
                    .map(row -> Arrays.stream(row.split("\\s+"))
                            .map(cell -> {
                                if (cell.contains("/")) {
                                    String[] parts = cell.split("/");
                                    return Fraction.of(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                                } else {
                                    return Fraction.of(Long.parseLong(cell), 1);
                                }
                            }).toArray(Fraction[]::new))
                    .toArray(Fraction[][]::new);
            return Matrix.of(matrixArray);
        } catch (Exception e) {
            throw new IllegalArgumentException("MatrixString is not a valid matrix");
        }
    }

    public static Matrix identityMatrix(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Matrix size must be at least 1");
        }
        Fraction[][] identityMatrixArray = new Fraction[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    identityMatrixArray[i][j] = Fraction.ONE;
                } else {
                    identityMatrixArray[i][j] = Fraction.ZERO;
                }
            }
        }
        return Matrix.of(identityMatrixArray);
    }

    public static Matrix zeroSquareMatrix(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Matrix size must be at least 1");
        }
        return zeroMatrix(size, size);
    }

    public static Matrix zeroMatrix(int rowsCount, int columnsCount) {
        if (rowsCount < 1 || columnsCount < 1) {
            throw new IllegalArgumentException("rowsCount and columnsCount must be at least 1");
        }
        Fraction[][] zeroMatrixArray = new Fraction[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                zeroMatrixArray[i][j] = Fraction.ZERO;
            }
        }
        return Matrix.of(zeroMatrixArray);
    }

    public static Matrix random(int rowsCount, int columnsCount) {
        if (rowsCount < 1 || columnsCount < 1) {
            throw new IllegalArgumentException("rowsCount and columnsCount must be at least 1");
        }
        Fraction[][] matrixArray = new Fraction[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                matrixArray[i][j] = Fraction.getRandom();
            }
        }
        return Matrix.of(matrixArray);
    }

    public static Matrix randomWithIntegers(int rowsCount, int columnsCount) {
        if (rowsCount < 1 || columnsCount < 1) {
            throw new IllegalArgumentException("rowsCount and columnsCount must be at least 1");
        }
        Fraction[][] integerMatrixArray = new Fraction[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                integerMatrixArray[i][j] = Fraction.randomUnitFraction();
            }
        }
        return Matrix.of(integerMatrixArray);
    }
}
