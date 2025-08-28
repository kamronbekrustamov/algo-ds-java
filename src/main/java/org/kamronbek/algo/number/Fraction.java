package org.kamronbek.algo.number;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

/**
 * An immutable, arbitrary-precision fraction class.
 * <p>
 * This class represents a fraction using {@link BigInteger} for the numerator and denominator,
 * making it safe from overflow. Fractions are always stored in their simplest, canonical form,
 * meaning they are reduced to their lowest terms and the sign is always carried by the numerator.
 */
public class Fraction implements Comparable<Fraction> {
    private final BigInteger numerator;
    private final BigInteger denominator;
    private static final Random random = new Random();
    /** A constant representing the fraction 0/1. */
    public static final Fraction ZERO = new Fraction(BigInteger.ZERO, BigInteger.ONE);
    /** A constant representing the fraction 1/1. */
    public static final Fraction ONE = new Fraction(BigInteger.ONE, BigInteger.ONE);


    /**
     * Private constructor. Creates a fraction and reduces it to its canonical form.
     *
     * @param numerator   the numerator of the fraction.
     * @param denominator the denominator of the fraction (must not be zero).
     */
    private Fraction(BigInteger numerator, BigInteger denominator) {
        if (denominator.signum() == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }

        // Ensure the sign is stored only in the numerator
        if (denominator.signum() < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        // Reduce the fraction by dividing by the greatest common divisor.
        BigInteger commonDivisor = numerator.gcd(denominator);
        this.numerator = numerator.divide(commonDivisor);
        this.denominator = denominator.divide(commonDivisor);
    }

    /**
     * Gets the numerator of the fraction.
     * @return the numerator as a {@link BigInteger}.
     */
    public BigInteger getNumerator() {
        return numerator;
    }

    /**
     * Gets the denominator of the fraction.
     * @return the denominator as a {@link BigInteger} (always positive).
     */
    public BigInteger getDenominator() {
        return denominator;
    }

    /**
     * Factory method to create a {@code Fraction} from {@code long} values.
     *
     * @param numerator   the numerator.
     * @param denominator the denominator.
     * @return a new {@code Fraction} instance.
     */
    public static Fraction of(long numerator, long denominator) {
        return new Fraction(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    /**
     * Factory method to create a {@code Fraction} from {@code BigInteger} values.
     *
     * @param numerator   the numerator.
     * @param denominator the denominator.
     * @return a new {@code Fraction} instance.
     */
    public static Fraction of(BigInteger numerator, BigInteger denominator) {
        return new Fraction(numerator, denominator);
    }

    /**
     * Adds another fraction to this fraction.
     *
     * @param addend the fraction to add.
     * @return a new {@code Fraction} representing the sum.
     */
    public Fraction add(Fraction addend) {
        // a/b + c/d = (ad + bc) / bd
        BigInteger newNumerator = this.numerator.multiply(addend.denominator)
                .add(addend.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(addend.denominator);
        return new Fraction(newNumerator, newDenominator);
    }

    /**
     * Subtracts another fraction from this fraction.
     *
     * @param subtrahend the fraction to subtract.
     * @return a new {@code Fraction} representing the difference.
     */
    public Fraction subtract(Fraction subtrahend) {
        // a/b - c/d = (ad - bc) / bd
        BigInteger newNumerator = this.numerator.multiply(subtrahend.denominator)
                .subtract(subtrahend.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(subtrahend.denominator);
        return new Fraction(newNumerator, newDenominator);
    }

    /**
     * Multiplies this fraction by another fraction.
     * <p>
     * This implementation uses cross-cancellation before multiplication to keep intermediate numbers smaller.
     *
     * @param multiplier the fraction to multiply by.
     * @return a new {@code Fraction} representing the product.
     */
    public Fraction multiply(Fraction multiplier) {
        // Use cross-cancellation to keep intermediate numbers smaller.
        BigInteger common1 = this.numerator.gcd(multiplier.denominator);
        BigInteger common2 = multiplier.numerator.gcd(this.denominator);

        BigInteger newNumerator = this.numerator.divide(common1).multiply(multiplier.numerator.divide(common2));
        BigInteger newDenominator = this.denominator.divide(common2).multiply(multiplier.denominator.divide(common1));

        return new Fraction(newNumerator, newDenominator);
    }

    /**
     * Divides this fraction by another fraction.
     *
     * @param divisor the fraction to divide by.
     * @return a new {@code Fraction} representing the result of the division.
     * @throws ArithmeticException if the divisor is zero.
     */
    public Fraction divide(Fraction divisor) {
        if (divisor.isZero()) {
            throw new ArithmeticException("Divisor cannot be zero");
        }
        // Division is multiplication by the reciprocal.
        return this.multiply(divisor.reciprocal());
    }

    /**
     * Returns the reciprocal of this fraction (denominator / numerator).
     *
     * @return a new {@code Fraction} representing the reciprocal.
     * @throws ArithmeticException if this fraction is zero.
     */
    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
    }

    /**
     * Returns the absolute value of this fraction.
     *
     * @return a new {@code Fraction} with a non-negative value.
     */
    public Fraction abs() {
        return new Fraction(numerator.abs(), denominator);
    }

    /**
     * Checks if this fraction is equal to zero.
     *
     * @return {@code true} if the fraction is zero, {@code false} otherwise.
     */
    public boolean isZero() {
        return numerator.signum() == 0;
    }

    /**
     * Checks if this fraction is equal to one.
     *
     * @return {@code true} if the fraction is one, {@code false} otherwise.
     */
    public boolean isOne() {
        return numerator.equals(BigInteger.ONE) && denominator.equals(BigInteger.ONE);
    }

    /**
     * Generates a random fraction.
     * <p>
     * The numerator and denominator are random {@code long} values between 1 and 19.
     * The numerator may be negative.
     *
     * @return a new random {@code Fraction}.
     */
    public static Fraction getRandom() {
        long num = random.nextLong(1, 20);
        long den = random.nextLong(1, 20);
        if (random.nextBoolean()) {
            num = -num;
        }
        return Fraction.of(num, den);
    }

    /**
     * Generates a random "unit" fraction (a fraction with a denominator of 1).
     *
     * @return a new random {@code Fraction} with a denominator of 1.
     */
    public static Fraction randomUnitFraction() {
        long num = random.nextLong(1, 20);
        if (random.nextBoolean()) {
            num = -num;
        }
        return Fraction.of(num, 1);
    }

    @Override
    public int compareTo(Fraction fraction) {
        // To compare a/b and c/d, we compare a*d and c*b, which avoids large intermediate LCMs.
        return this.numerator.multiply(fraction.denominator)
                .compareTo(fraction.numerator.multiply(this.denominator));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        // Since fractions are always in reduced, canonical form, direct comparison is sufficient.
        return numerator.equals(fraction.numerator) && denominator.equals(fraction.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator.toString();
        return numerator + "/" + denominator;
    }
}
