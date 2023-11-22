package org.kamronbek.algo.number;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Cryptography {

    // Generates a list of public and private key combinations
    // Returns List[public key exponent, private key exponent, mod]
    public static List<Long> rsa() {
        Random random = new Random();
        List<Long> primesUnder1000 = CommonAlgorithms.primesBetween(30000, 40000);
        long p = primesUnder1000.get(random.nextInt(primesUnder1000.size()));
        primesUnder1000.remove(p);
        long q = primesUnder1000.get(random.nextInt(primesUnder1000.size()));
        long n = p * q;
        long phi = (p - 1) * (q - 1); // Euler's totient function
        List<Long> primesBetween1000andPhi = CommonAlgorithms.relativelyPrimesBetween(phi, 1000, 100000);
        long e = primesBetween1000andPhi.get(random.nextInt(primesBetween1000andPhi.size()));
        long d = CommonAlgorithms.modularMultiplicativeInverse(e, phi);
        return Arrays.asList(e, d, n);
    }

    // Encryption and Decryption functions are not perfect, and they need to be used carefully to avoid data inconsistency.
    // These are the basic implementations of the encrypt and decrypt functions.
    // Things to consider furthermore: (val < mod) must be true, and the overflow should not happen during calculation
    public static long rsaEncrypt(long val, long publicKey, long mod) {
        return CommonAlgorithms.fastModularExponentiation(val, publicKey, mod);
    }

    public static long rsaDecrypt(long val, long privateKey, long mod) {
        return CommonAlgorithms.fastModularExponentiation(val, privateKey, mod);
    }
}
