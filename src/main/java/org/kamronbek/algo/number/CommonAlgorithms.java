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
