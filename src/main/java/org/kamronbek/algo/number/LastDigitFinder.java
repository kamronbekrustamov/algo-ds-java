package org.kamronbek.algo.number;

import java.util.HashMap;
import java.util.Map;

/*
* when the a^b is given, finds the last digit of the solution
* */
public class LastDigitFinder {
    private static Map<Integer, Integer> exponents = new HashMap<>();

    static {
        exponents.put(0, 1);
        exponents.put(1, 1);
        exponents.put(2, 4);
        exponents.put(3, 4);
        exponents.put(4, 2);
        exponents.put(5, 1);
        exponents.put(6, 1);
        exponents.put(7, 4);
        exponents.put(8, 4);
        exponents.put(9, 2);

    }

    public static int findTheLastDigit(int base, int exponent) {
        int baseLastDigit = base % 10;
        int exponentToCheck = exponent % exponents.get(baseLastDigit);
        if (exponentToCheck == 0) {
            return ((int) Math.pow(baseLastDigit, exponents.get(baseLastDigit))) % 10;
        } else {
            return ((int) Math.pow(baseLastDigit, exponentToCheck)) % 10;
        }
    }
}
