package org.kamronbek.algo.number;

import java.util.List;
import java.util.stream.LongStream;

public class CommonAlgorithms {

    private CommonAlgorithms() {}

    // Find the GCD using Euclidean algorithm
    public static long gcd(long first, long second) {
        if (first <= 0) {
            throw new IllegalArgumentException("Numbers must be greater than 0");
        }
        if (second <= 0) {
            throw new IllegalArgumentException("Numbers must be greater than 0");
        }
        while (second != 0) {
            long temp = second;
            second = first % second;
            first = temp;
        }
        return first;
    }

    // Find the modular multiplicative inverse using Extended Euclidean algorithm
    public static long modularMultiplicativeInverse(long num, long mod) {
        if (gcd(num, mod) != 1) {
            throw new IllegalArgumentException("Numbers must be relatively prime");
        }

        // Making sure that a >= b
        long a = mod;
        long b = num % mod; // if the num is higher than mod, then making it less than mod
        long quotient = a / b;
        long remainder = a % b;
        long t1 = 0;
        long t2 = 1;
        long t = t1 - t2 * quotient;

        while (remainder != 0) {
            a = b;
            b = remainder;
            quotient = a / b;
            remainder = a % b;
            t1 = t2;
            t2 = t;
            t = t1 - t2 * quotient;
        }

        // If the modular multiplicative inverse is negative, making it positive
        while (t2 < 0) {
            t2 += mod;
        }
        return t2;
    }

    public static List<Long> primesLessThan(long upperBound) {
        return LongStream.range(2, upperBound).filter(CommonAlgorithms::isPrime).boxed().toList();
    }

    public static long countOfPrimesLessThan(long upperBound) {
        return LongStream.range(2, upperBound).filter(CommonAlgorithms::isPrime).count();
    }

    public static boolean isPrime(long num) {
        long squareRoot = (long) Math.sqrt(num);
        for (long i = 2; i <= squareRoot; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
