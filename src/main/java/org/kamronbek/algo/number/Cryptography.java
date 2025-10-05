package org.kamronbek.algo.number;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class provides basic cryptographic functions, including RSA key generation, encryption, and decryption.
 * Note: This implementation is for educational purposes and may not be suitable for production use
 * due to its use of non-secure random number generation and potentially inefficient algorithms.
 */
public class Cryptography {

    /**
     * Generates a pair of public and private keys for the RSA algorithm.
     *
     * The process involves:
     * 1. Selecting two large, distinct prime numbers, p and q.
     * 2. Calculating the modulus, n = p * q.
     * 3. Calculating Euler's totient function, phi = (p - 1) * (q - 1).
     * 4. Choosing a public exponent, e, such that 1 < e < phi and gcd(e, phi) = 1.
     * 5. Calculating the private exponent, d, as the modular multiplicative inverse of e modulo phi.
     *
     * @return A list containing the public exponent (e), private exponent (d), and modulus (n).
     *         The list is structured as [e, d, n].
     */
    public static List<Long> rsa() {
        Random random = new Random();

        // 1. Select two distinct large prime numbers, p and q.
        List<Long> primes = CommonAlgorithms.primesBetween(30000, 40000);
        long p = primes.get(random.nextInt(primes.size()));
        primes.remove(p); // Ensure q is different from p
        long q = primes.get(random.nextInt(primes.size()));

        // 2. Calculate n (the modulus for the public and private keys).
        long n = p * q;

        // 3. Calculate phi(n) (Euler's totient function).
        long phi = (p - 1) * (q - 1);

        // 4. Choose a public exponent 'e' that is relatively prime to phi.
        List<Long> relativelyPrimes = CommonAlgorithms.relativelyPrimesBetween(phi, 1000, 100000);
        long e = relativelyPrimes.get(random.nextInt(relativelyPrimes.size()));

        // 5. Calculate the private exponent 'd', the modular multiplicative inverse of 'e' mod 'phi'.
        long d = CommonAlgorithms.modularMultiplicativeInverse(e, phi);

        // Return the public key (e, n) and private key (d, n).
        return Arrays.asList(e, d, n);
    }

    /**
     * Encrypts a value using the RSA public key.
     *
     * This is a basic implementation. For this encryption to work correctly, the value to be encrypted
     * must be less than the modulus. Additionally, this implementation does not handle potential overflows
     * during calculation, which can lead to data inconsistency.
     *
     * @param val       The value to encrypt.
     * @param publicKey The public exponent (e).
     * @param mod       The modulus (n).
     * @return The encrypted value.
     */
    public static long rsaEncrypt(long val, long publicKey, long mod) {
        return CommonAlgorithms.fastModularExponentiation(val, publicKey, mod);
    }

    /**
     * Decrypts a value using the RSA private key.
     *
     * @param val        The encrypted value to decrypt.
     * @param privateKey The private exponent (d).
     * @param mod        The modulus (n).
     * @return The decrypted (original) value.
     */
    public static long rsaDecrypt(long val, long privateKey, long mod) {
        return CommonAlgorithms.fastModularExponentiation(val, privateKey, mod);
    }
}
