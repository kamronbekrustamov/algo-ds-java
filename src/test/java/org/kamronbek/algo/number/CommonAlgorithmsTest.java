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
}