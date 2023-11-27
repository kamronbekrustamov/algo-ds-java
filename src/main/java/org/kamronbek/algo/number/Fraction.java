package org.kamronbek.algo.number;

import java.util.Objects;
import java.util.Random;

public class Fraction implements Comparable<Fraction> {
    private final long numerator;
    private final long denominator;
    private static final Random random = new Random();
    public static final Fraction ZERO = Fraction.of(0, 1);
    public static final Fraction ONE = Fraction.of(1, 1);


    // stores the fraction always in the reduced form
    // operations does not consider the usage of numbers which may cause overflow
    private Fraction(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else if (denominator < 0) {
            // storing the negative number only in numerator
            long gcd = gcd(numerator, denominator);
            this.numerator = -1 * numerator / gcd;
            this.denominator = -1 * denominator / gcd;
        } else {
            long gcd = gcd(numerator, denominator);
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    public static Fraction of(long numerator, long denominator) {
        return new Fraction(numerator, denominator);
    }

    public Fraction add(Fraction addend) {
        long lcm = lcm(denominator, addend.denominator);
        long numeratorAddition = numerator * (lcm / denominator) + addend.numerator * (lcm / addend.denominator);
        return Fraction.of(numeratorAddition, lcm);
    }

    public Fraction subtract(Fraction subtrahend) {
        long lcm = lcm(denominator, subtrahend.denominator);
        long numeratorSubtraction = numerator * (lcm / denominator) - subtrahend.numerator * (lcm / subtrahend.denominator);
        return Fraction.of(numeratorSubtraction, lcm);
    }

    public Fraction multiply(Fraction multiplier) {
        return Fraction.of(numerator * multiplier.numerator, denominator * multiplier.denominator);
    }

    public Fraction divide(Fraction divisor) {
        if (divisor.isZero()) {
            throw new ArithmeticException("Divisor cannot be zero");
        }
        return Fraction.of(numerator * divisor.denominator, denominator * divisor.numerator);
    }

    public Fraction abs() {
        return Fraction.of(Math.abs(numerator), denominator);
    }

    public boolean isZero() {
        return numerator == 0;
    }

    public static Fraction getRandom() {
        long numerator = random.nextLong(1, 20);
        long denominator = random.nextLong(1, 20);
        long sign = random.nextInt(0, 2) == 0 ? 1 : -1;
        return Fraction.of(sign * numerator, denominator);
    }

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

    @Override
    public int compareTo(Fraction fraction) {
        long lcm = lcm(denominator, fraction.denominator);
        return Long.compare(numerator * (lcm / denominator), fraction.numerator * (lcm / fraction.denominator));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        if (denominator == 1)
            return String.valueOf(numerator);
        return numerator + "/" + denominator;
    }
}
