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

    /**
     * Checks if a number is prime using an optimized trial division method.
     * <p>
     * A number is prime if it is greater than 1 and has no positive divisors other than 1 and itself.
     * This implementation first checks for small special cases (numbers less than 2, 2 itself, and other even numbers)
     * and then checks for divisibility by odd numbers up to the square root of the number.
     *
     * @param num the number to check.
     * @return {@code true} if the number is prime, {@code false} otherwise.
     */
    public static boolean isPrime(long num) {
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        long squareRoot = (long) Math.sqrt(num);
        for (long i = 3; i <= squareRoot; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates (base ^ exponent) % mod using the right-to-left binary method
     * (also known as exponentiation by squaring). This is highly efficient for large exponents.
     *
     * @param base     the base of the exponentiation (can be negative)
     * @param exponent the exponent (must be non-negative)
     * @param mod      the modulus (must be greater than 1)
     * @return the value of (base ^ exponent) % mod
     */
    public static long fastModularExponentiation(long base, long exponent, long mod) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        }
        if (mod <= 1) {
            if (mod == 1) {
                return 0;
            }
            throw new IllegalArgumentException("Modulus must be greater than 1.");
        }

        long result = 1;
        // Reduce base with mod at the start and handle negative base.
        // The result of % in Java can be negative, so we adjust it to be in [0, mod-1].
        long b = (base % mod + mod) % mod;
        while (exponent > 0) {
            if ((exponent & 1) == 1) result = (result * b) % mod;
            b = (b * b) % mod;
            exponent >>= 1; // Move to the next bit (equivalent to exponent / 2)
        }
        return result;
    }
}
