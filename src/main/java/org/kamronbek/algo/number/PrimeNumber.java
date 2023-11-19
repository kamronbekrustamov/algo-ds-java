package org.kamronbek.algo.number;

import java.util.List;
import java.util.stream.IntStream;

public class PrimeNumber {
    public static List<Integer> primesLessThan(int upperBound) {
        return IntStream.range(2, upperBound).filter(PrimeNumber::isPrime).boxed().toList();
    }

    public static long countOfPrimesLessThan(int upperBound) {
        return IntStream.range(2, upperBound).filter(PrimeNumber::isPrime).count();
    }

    public static boolean isPrime(int num) {
        int squareRoot = (int) Math.sqrt(num);
        for (int i = 2; i <= squareRoot; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
