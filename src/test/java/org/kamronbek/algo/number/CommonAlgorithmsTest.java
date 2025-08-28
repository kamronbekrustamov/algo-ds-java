package org.kamronbek.algo.number;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonAlgorithmsTest {

    // === gcd Tests ===
    @Test
    void testGcd_SimpleCase() {
        assertEquals(6, CommonAlgorithms.gcd(54, 24));
    }

    @Test
    void testGcd_RelativelyPrime() {
        assertEquals(1, CommonAlgorithms.gcd(17, 13));
    }

    @Test
    void testGcd_WithZero() {
        assertEquals(10, CommonAlgorithms.gcd(10, 0));
        assertEquals(10, CommonAlgorithms.gcd(0, 10));
    }

    @Test
    void testGcd_BothZero() {
        assertEquals(0, CommonAlgorithms.gcd(0, 0));
    }

    @Test
    void testGcd_WithNegativeNumbers() {
        assertEquals(6, CommonAlgorithms.gcd(-54, 24));
        assertEquals(6, CommonAlgorithms.gcd(54, -24));
        assertEquals(6, CommonAlgorithms.gcd(-54, -24));
    }

    // === lcm Tests ===
    @Test
    void testLcm_SimpleCase() {
        assertEquals(12, CommonAlgorithms.lcm(4, 6));
    }

    @Test
    void testLcm_WithZero() {
        assertEquals(0, CommonAlgorithms.lcm(10, 0));
        assertEquals(0, CommonAlgorithms.lcm(0, 10));
    }

    @Test
    void testLcm_WithNegativeNumbers() {
        assertEquals(12, CommonAlgorithms.lcm(-4, 6));
        assertEquals(12, CommonAlgorithms.lcm(4, -6));
        assertEquals(12, CommonAlgorithms.lcm(-4, -6));
    }

    // === isPrime Tests ===
    @Test
    void testIsPrime_Primes() {
        assertTrue(CommonAlgorithms.isPrime(2));
        assertTrue(CommonAlgorithms.isPrime(3));
        assertTrue(CommonAlgorithms.isPrime(7));
        assertTrue(CommonAlgorithms.isPrime(97));
    }

    @Test
    void testIsPrime_Composites() {
        assertFalse(CommonAlgorithms.isPrime(4));
        assertFalse(CommonAlgorithms.isPrime(9));
        assertFalse(CommonAlgorithms.isPrime(100));
    }

    @Test
    void testIsPrime_EdgeCases() {
        assertFalse(CommonAlgorithms.isPrime(0));
        assertFalse(CommonAlgorithms.isPrime(1));
        assertFalse(CommonAlgorithms.isPrime(-7));
    }

    // === fastModularExponentiation Tests ===
    @Test
    void testFastModularExponentiation_SimpleCase() {
        // 3^13 % 7 = 3
        assertEquals(3, CommonAlgorithms.fastModularExponentiation(3, 13, 7));
    }

    @Test
    void testFastModularExponentiation_ZeroExponent() {
        assertEquals(1, CommonAlgorithms.fastModularExponentiation(123, 0, 456));
    }

    @Test
    void testFastModularExponentiation_ZeroBase() {
        assertEquals(0, CommonAlgorithms.fastModularExponentiation(0, 123, 456));
    }

    @Test
    void testFastModularExponentiation_NegativeBase() {
        // (-3)^13 % 7 = (-3 % 7)^13 % 7 = 4^13 % 7 = 4
        assertEquals(4, CommonAlgorithms.fastModularExponentiation(-3, 13, 7));
    }

        // === modularMultiplicativeInverse Tests ===
    @Test
    void testModularInverse_SimpleCase() {
        // The inverse of 3 mod 11 is 4, because (3 * 4) % 11 = 12 % 11 = 1.
        assertEquals(4, CommonAlgorithms.modularMultiplicativeInverse(3, 11));
    }

    @Test
    void testModularInverse_AnotherCase() {
        // The inverse of 7 mod 26 is 15, because (7 * 15) % 26 = 105 % 26 = 1.
        assertEquals(15, CommonAlgorithms.modularMultiplicativeInverse(7, 26));
    }

    @Test
    void testModularInverse_NumGreaterThanMod() {
        // 14 is congruent to 3 (mod 11), so the inverse is the same as for 3.
        assertEquals(4, CommonAlgorithms.modularMultiplicativeInverse(14, 11));
    }

    @Test
    void testModularInverse_NegativeNum() {
        // -3 is congruent to 8 (mod 11). The inverse of 8 is 7, because (8 * 7) % 11 = 56 % 11 = 1.
        assertEquals(7, CommonAlgorithms.modularMultiplicativeInverse(-3, 11));
    }

    @Test
    void testModularInverse_One() {
        // The inverse of 1 is always 1.
        assertEquals(1, CommonAlgorithms.modularMultiplicativeInverse(1, 26));
    }

    @Test
    void testModularInverse_ThrowsExceptionWhenNotRelativelyPrime() {
        // gcd(4, 10) = 2, so no inverse exists.
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> CommonAlgorithms.modularMultiplicativeInverse(4, 10)
        );
        assertEquals("Modular inverse does not exist because numbers are not relatively prime.", exception.getMessage());
    }

    @Test
    void testModularInverse_ThrowsExceptionForInvalidMod() {
        // Modulus must be > 1.
        assertThrows(ArithmeticException.class, () -> CommonAlgorithms.modularMultiplicativeInverse(5, 1));
        assertThrows(ArithmeticException.class, () -> CommonAlgorithms.modularMultiplicativeInverse(5, 0));
        assertThrows(ArithmeticException.class, () -> CommonAlgorithms.modularMultiplicativeInverse(5, -10));
    }
}