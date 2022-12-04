package org.kamronbek.algo.multiplyandsquare;

public class MultiplyAndSquare {
    public long calculateRemainder(long base, long exponent, long divisor) {
        return Long.toBinaryString(exponent).chars().asLongStream().reduce(1,
                (remainder, binaryDigitCode) -> {
                    if (binaryDigitCode == 48) {
                        return remainder * remainder % divisor;
                    } else {
                        return remainder * remainder * base % divisor;
                    }
        });
    }
}
