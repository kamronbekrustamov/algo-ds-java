package org.kamronbek.algo.number;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CommonAlgorithms {

    private CommonAlgorithms() {}

    /**
     * Calculates the greatest common divisor (GCD) of two numbers using the Euclidean algorithm.
     * The GCD of two integers is the largest positive integer that divides both numbers without a remainder.
     * This implementation handles non-positive inputs by taking their absolute values.
     * By definition, gcd(n, 0) = |n|.
     *
     * @param a the first number.
     * @param b the second number.
     * @return the greatest common divisor of {@code a} and {@code b}.
     */
    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        // The Euclidean algorithm correctly handles a zero input, e.g., gcd(n, 0) == n.
        // The only edge case is gcd(0, 0), which will correctly result in 0.
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calculates the least common multiple (LCM) of two numbers.
     * The LCM of two integers is the smallest positive integer that is divisible by both numbers.
     * The calculation uses the formula: lcm(a, b) = (|a * b|) / gcd(a, b).
     * This implementation returns 0 if either input is 0.
     *
     * @param a the first number.
     * @param b the second number.
     * @return the least common multiple of {@code a} and {@code b}.
     */
    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        a = Math.abs(a);
        b = Math.abs(b);
        long gcd = gcd(a, b);
        // Calculate as (first / gcd) * second to prevent potential overflow of (first * second)
        return (a / gcd) * b;
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

    /**
     * Finds all prime numbers less than a given number.
     *
     * @param endExclusive The upper bound (exclusive).
     * @return A list of all prime numbers from 2 up to (but not including) {@code endExclusive}.
     */
    public static List<Long> primesLessThan(long endExclusive) {
        return primesBetween(2, endExclusive);
    }

    /**
     * Finds all prime numbers within a given range.
     *
     * @param startInclusive The lower bound (inclusive).
     * @param endExclusive   The upper bound (exclusive).
     * @return A list of prime numbers in the specified range.
     */
    public static List<Long> primesBetween(long startInclusive, long endExclusive) {
        return LongStream.range(startInclusive, endExclusive).filter(CommonAlgorithms::isPrime).boxed().collect(Collectors.toList());
    }

    /**
     * Counts the number of primes less than a given number.
     *
     * @param endExclusive The upper bound (exclusive).
     * @return The count of prime numbers from 2 up to (but not including) {@code endExclusive}.
     */
    public static long countOfPrimesLessThan(long endExclusive) {
        return LongStream.range(2, endExclusive).filter(CommonAlgorithms::isPrime).count();
    }

    /**
     * Counts the number of primes within a given range.
     *
     * @param startInclusive The lower bound (inclusive).
     * @param endExclusive   The upper bound (exclusive).
     * @return The count of prime numbers in the specified range.
     */
    public static long countOfPrimesBetween(long startInclusive, long endExclusive) {
        return LongStream.range(startInclusive, endExclusive).filter(CommonAlgorithms::isPrime).count();
    }

    /**
     * Finds all numbers less than a given value that are relatively prime to {@code num}.
     * Two numbers are relatively prime if their greatest common divisor (GCD) is 1.
     *
     * @param num          The number to check for relative primality against.
     * @param endExclusive The upper bound (exclusive) for the numbers to check.
     * @return A list of numbers from 1 to {@code endExclusive} that are relatively prime to {@code num}.
     */
    public static List<Long> relativelyPrimesLessThan(long num, long endExclusive) {
        return relativelyPrimesBetween(num, 1, endExclusive);
    }

    /**
     * Finds all numbers in a given range that are relatively prime to {@code num}.
     * Two numbers are relatively prime if their greatest common divisor (GCD) is 1.
     *
     * @param num            The number to check for relative primality against.
     * @param startInclusive The lower bound (inclusive) for the numbers to check.
     * @param endExclusive   The upper bound (exclusive) for the numbers to check.
     * @return A list of numbers in the specified range that are relatively prime to {@code num}.
     */
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
