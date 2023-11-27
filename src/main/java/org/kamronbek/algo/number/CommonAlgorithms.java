package org.kamronbek.algo.number;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CommonAlgorithms {

    private CommonAlgorithms() {}

    // Find the GCD using Euclidean algorithm
    public static long gcd(long first, long second) {
        if (first == 0 || second == 0) {
            throw new ArithmeticException("Numbers cannot be zero");
        }
        first = Math.abs(first);
        second = Math.abs(second);
        while (second != 0) {
            long temp = second;
            second = first % second;
            first = temp;
        }
        return first;
    }

    public static long lcm(long first, long second) {
        first = Math.abs(first);
        second = Math.abs(second);
        long gcd = gcd(first, second);
        return first / gcd * second;
    }

    // Find the modular multiplicative inverse using Extended Euclidean algorithm
    public static long modularMultiplicativeInverse(long num, long mod) {
        if (gcd(num, mod) != 1) {
            throw new ArithmeticException("Numbers must be relatively prime");
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

    public static List<Long> primesLessThan(long endExclusive) {
        return primesBetween(2, endExclusive);
    }

    public static List<Long> primesBetween(long startInclusive, long endExclusive) {
        return LongStream.range(startInclusive, endExclusive).filter(CommonAlgorithms::isPrime).boxed().collect(Collectors.toList());
    }

    public static long countOfPrimesLessThan(long endExclusive) {
        return LongStream.range(2, endExclusive).filter(CommonAlgorithms::isPrime).count();
    }

    public static long countOfPrimesBetween(long startInclusive, long endExclusive) {
        return LongStream.range(startInclusive, endExclusive).filter(CommonAlgorithms::isPrime).count();
    }

    public static List<Long> relativeLyPrimesLessThan(long num, long endExclusive) {
        return relativelyPrimesBetween(num, 1, endExclusive);
    }

    public static List<Long> relativelyPrimesBetween(long num, long startInclusive, long endExclusive) {
        if (num < 1) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }
        return LongStream.range(startInclusive, endExclusive).filter(it -> gcd(it, num) == 1).boxed().collect(Collectors.toList());
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

    public static long fastModularExponentiation(long base, long exponent, long mod) {
        if (base < 0 || exponent < 0 || mod < 1) {
            throw new IllegalArgumentException("Base and exponent must not be negative integers, and mod must be higher than 0");
        }
        String binaryString = Long.toBinaryString(exponent);
        long result = 1;
        long power = base;
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            if (binaryString.charAt(i) == '1') {
                result = (result * power % mod);
            }
            power = power * power % mod;
        }
        return result;
    }
}
