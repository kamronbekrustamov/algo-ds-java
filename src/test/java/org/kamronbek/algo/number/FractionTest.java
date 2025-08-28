package org.kamronbek.algo.number;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void testCreationAndReduction() {
        // Simple creation
        Fraction f1 = Fraction.of(1, 2);
        assertEquals(BigInteger.valueOf(1), f1.getNumerator());
        assertEquals(BigInteger.valueOf(2), f1.getDenominator());

        // Reduction
        Fraction f2 = Fraction.of(2, 4);
        assertEquals(f1, f2);

        // Negative denominator
        Fraction f3 = Fraction.of(1, -2);
        assertEquals(BigInteger.valueOf(-1), f3.getNumerator());
        assertEquals(BigInteger.valueOf(2), f3.getDenominator());

        // Double negative
        Fraction f4 = Fraction.of(-1, -2);
        assertEquals(f1, f4);

        // Zero numerator
        Fraction f5 = Fraction.of(0, 5);
        assertEquals(Fraction.ZERO, f5);
        assertEquals(BigInteger.ZERO, f5.getNumerator());
        assertEquals(BigInteger.ONE, f5.getDenominator());
    }

    @Test
    void testCreationWithZeroDenominatorThrowsException() {
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> Fraction.of(1, 0)
        );
        assertEquals("Denominator cannot be zero", exception.getMessage());
    }

    @Test
    void testAddition() {
        Fraction f1 = Fraction.of(1, 2);
        Fraction f2 = Fraction.of(1, 3);
        Fraction result = f1.add(f2); // 1/2 + 1/3 = 3/6 + 2/6 = 5/6
        assertEquals(Fraction.of(5, 6), result);

        Fraction f3 = Fraction.of(-1, 2);
        Fraction result2 = f1.add(f3); // 1/2 + (-1/2) = 0
        assertEquals(Fraction.ZERO, result2);
    }

    @Test
    void testSubtraction() {
        Fraction f1 = Fraction.of(1, 2);
        Fraction f2 = Fraction.of(1, 3);
        Fraction result = f1.subtract(f2); // 1/2 - 1/3 = 3/6 - 2/6 = 1/6
        assertEquals(Fraction.of(1, 6), result);

        Fraction result2 = f1.subtract(f1);
        assertEquals(Fraction.ZERO, result2);
    }

    @Test
    void testMultiplication() {
        Fraction f1 = Fraction.of(2, 3);
        Fraction f2 = Fraction.of(3, 4);
        Fraction result = f1.multiply(f2); // 2/3 * 3/4 = 6/12 = 1/2
        assertEquals(Fraction.of(1, 2), result);

        Fraction result2 = f1.multiply(Fraction.ZERO);
        assertEquals(Fraction.ZERO, result2);
    }

    @Test
    void testDivision() {
        Fraction f1 = Fraction.of(1, 2);
        Fraction f2 = Fraction.of(3, 4);
        Fraction result = f1.divide(f2); // 1/2 / 3/4 = 1/2 * 4/3 = 4/6 = 2/3
        assertEquals(Fraction.of(2, 3), result);
    }

    @Test
    void testDivisionByZeroThrowsException() {
        Fraction f1 = Fraction.of(1, 2);
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> f1.divide(Fraction.ZERO)
        );
        assertEquals("Divisor cannot be zero", exception.getMessage());
    }

    @Test
    void testReciprocal() {
        Fraction f1 = Fraction.of(2, 3);
        assertEquals(Fraction.of(3, 2), f1.reciprocal());

        assertThrows(ArithmeticException.class, Fraction.ZERO::reciprocal);
    }

    @Test
    void testAbs() {
        Fraction f1 = Fraction.of(-1, 2);
        Fraction f2 = Fraction.of(1, 2);
        assertEquals(f2, f1.abs());
        assertEquals(f2, f2.abs());
    }

    @Test
    void testComparison() {
        Fraction f1_2 = Fraction.of(1, 2);
        Fraction f1_3 = Fraction.of(1, 3);
        Fraction f2_4 = Fraction.of(2, 4);

        assertTrue(f1_2.compareTo(f1_3) > 0); // 1/2 > 1/3
        assertTrue(f1_3.compareTo(f1_2) < 0); // 1/3 < 1/2
        assertEquals(0, f1_2.compareTo(f2_4)); // 1/2 == 2/4

        Fraction neg_f1_2 = Fraction.of(-1, 2);
        Fraction neg_f1_3 = Fraction.of(-1, 3);
        assertTrue(neg_f1_2.compareTo(neg_f1_3) < 0); // -1/2 < -1/3
    }

    @Test
    void testEqualsAndHashCode() {
        Fraction f1 = Fraction.of(1, 2);
        Fraction f2 = Fraction.of(1, 2);
        Fraction f3 = Fraction.of(2, 4); // Reduces to 1/2
        Fraction f4 = Fraction.of(1, 3);

        assertEquals(f1, f2);
        assertEquals(f1, f3);
        assertNotEquals(f1, f4);
        assertNotEquals(f1, null);
        assertNotEquals(f1, new Object());

        assertEquals(f1.hashCode(), f2.hashCode());
        assertEquals(f1.hashCode(), f3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("1/2", Fraction.of(1, 2).toString());
        assertEquals("-1/2", Fraction.of(-1, 2).toString());
        assertEquals("5", Fraction.of(5, 1).toString());
        assertEquals("0", Fraction.ZERO.toString());
    }

    @Test
    void testBigIntegerSupport_HandlesLargeNumbers() {
        // This test verifies that the class can handle numbers that would overflow a long.
        BigInteger largeNum = BigInteger.valueOf(Long.MAX_VALUE);
        Fraction f1 = Fraction.of(largeNum, BigInteger.ONE);
        Fraction f2 = Fraction.of(BigInteger.ONE, BigInteger.ONE);

        // Test addition that would overflow a long
        Fraction sum = f1.add(f2);
        BigInteger expectedNumerator = largeNum.add(BigInteger.ONE);
        assertEquals(expectedNumerator, sum.getNumerator());
        assertEquals(BigInteger.ONE, sum.getDenominator());

        // Test multiplication that would overflow a long
        Fraction product = f1.multiply(Fraction.of(2, 1));
        BigInteger expectedProductNumerator = largeNum.multiply(BigInteger.TWO);
        assertEquals(expectedProductNumerator, product.getNumerator());
    }

    @Test
    void testRandomMethods_ProduceValidFractions() {
        for (int i = 0; i < 100; i++) {
            assertNotNull(Fraction.getRandom());
            assertEquals(BigInteger.ONE, Fraction.randomUnitFraction().getDenominator());
        }
    }
}
