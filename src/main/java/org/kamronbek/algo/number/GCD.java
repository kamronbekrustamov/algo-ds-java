package org.kamronbek.algo.number;

public class GCD {
    public static int gcd(int first, int second) {
        if (first <= 0) {
            throw new IllegalArgumentException("First argument must be greater than 0");
        }
        if (second <= 0) {
            throw new IllegalArgumentException("Second argument must be greater than 0");
        }
        while (second != 0) {
            int temp = second;
            second = first % second;
            first = temp;
        }
        return first;
    }
}
