package org.kamronbek.algo.string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the KMPSearch algorithm, covering both the failure function
 * generation and the pattern search logic.
 */
public class KMPSearchTest {

    private KMPSearch kmpSearch;

    @BeforeEach
    void setUp() {
        kmpSearch = new KMPSearch();
    }

    // =================================================================================
    // 1. Failure Function (LPS Array) Tests
    // =================================================================================

    /**
     * Test a pattern with simple, non-overlapping repetitions.
     * Pattern: "ABCDE" -> LPS: [0, 0, 0, 0, 0]
     */
    @Test
    void testFailureFunction_NoRepetition() {
        int[] expected = {0, 0, 0, 0, 0};
        assertArrayEquals(expected, kmpSearch.createFailureFunction("ABCDE"));
    }

    /**
     * Test a pattern with alternating characters resulting in increasing/decreasing LPS.
     * Pattern: "ABABA"
     * A: 0
     * AB: 0
     * ABA: 1 (A)
     * ABA B: 2 (AB)
     * ABABA: 3 (ABA)
     */
    @Test
    void testFailureFunction_AlternatingRepetition() {
        int[] expected = {0, 0, 1, 2, 3};
        assertArrayEquals(expected, kmpSearch.createFailureFunction("ABABA"));
    }

    /**
     * Test a pattern with complex overlapping repetitions (a standard test case).
     * Pattern: "ABAABC"
     * A: 0
     * AB: 0
     * ABA: 1
     * ABAA: 1
     * ABAAB: 2
     * ABAABC: 0
     */
    @Test
    void testFailureFunction_ComplexOverlap() {
        int[] expected = {0, 0, 1, 1, 2, 0};
        assertArrayEquals(expected, kmpSearch.createFailureFunction("ABAABC"));
    }

    /**
     * Test a pattern with continuous identical characters.
     * Pattern: "AAAAA" -> LPS: [0, 1, 2, 3, 4]
     */
    @Test
    void testFailureFunction_AllSameCharacter() {
        int[] expected = {0, 1, 2, 3, 4};
        assertArrayEquals(expected, kmpSearch.createFailureFunction("AAAAA"));
    }

    /**
     * Test a single character pattern.
     */
    @Test
    void testFailureFunction_SingleCharacter() {
        int[] expected = {0};
        assertArrayEquals(expected, kmpSearch.createFailureFunction("A"));
    }

    /**
     * Test an empty pattern (should return an empty array).
     */
    @Test
    void testFailureFunction_EmptyPattern() {
        int[] expected = {};
        assertArrayEquals(expected, kmpSearch.createFailureFunction(""));
    }

    // =================================================================================
    // 2. Search Logic Tests
    // =================================================================================

    /**
     * Test simple single occurrence at the beginning.
     */
    @Test
    void testSearch_SingleOccurrence_Beginning() {
        String text = "TESTTEXT";
        String pattern = "TEST";
        List<Integer> expected = Collections.singletonList(0);
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test simple single occurrence in the middle.
     */
    @Test
    void testSearch_SingleOccurrence_Middle() {
        String text = "PREPARETESTEND";
        String pattern = "TEST";
        List<Integer> expected = Collections.singletonList(7);
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test simple single occurrence at the end.
     */
    @Test
    void testSearch_SingleOccurrence_End() {
        String text = "STARTWITHEND";
        String pattern = "END";
        List<Integer> expected = Collections.singletonList(9);
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test multiple non-overlapping occurrences.
     */
    @Test
    void testSearch_MultipleNonOverlapping() {
        String text = "ABCA B C A B C ABC";
        String pattern = "ABC";
        // Corrected expected indices: 0 and 15
        List<Integer> expected = Arrays.asList(0, 15);
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test the key advantage of KMP: handling multiple **overlapping** occurrences.
     * Text: "ABABA BABA"
     * Occurrences start at index 0 and 2.
     */
    @Test
    void testSearch_MultipleOverlapping() {
        String text = "ABABAABABA";
        String pattern = "ABA";
        List<Integer> expected = Arrays.asList(0, 2, 5, 7);
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test a case where the pattern is not found.
     */
    @Test
    void testSearch_NotFound() {
        String text = "ABCDEFGH";
        String pattern = "XYZ";
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test case where the pattern is identical to the text.
     */
    @Test
    void testSearch_PatternIsText() {
        String text = "PATTERN";
        String pattern = "PATTERN";
        List<Integer> expected = Collections.singletonList(0);
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test edge case where pattern length equals text length, but no match.
     */
    @Test
    void testSearch_LengthMatchNoMatch() {
        String text = "ABCDE";
        String pattern = "FGHIJ";
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    // =================================================================================
    // 3. Edge Case/Invalid Input Tests
    // =================================================================================

    /**
     * Test with an empty pattern.
     */
    @Test
    void testSearch_EmptyPattern() {
        String text = "AnyText";
        String pattern = "";
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test with a null text input.
     */
    @Test
    void testSearch_NullText() {
        String text = null;
        String pattern = "Pat";
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, kmpSearch.search(text, pattern));
    }

    /**
     * Test with a null pattern input.
     */
    @Test
    void testSearch_NullPattern() {
        String text = "Text";
        String pattern = null;
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, kmpSearch.search(text, pattern));
    }
}