package org.kamronbek.algo.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements the **Knuth-Morris-Pratt (KMP) Algorithm** for efficient pattern searching
 * within a text string.
 * KMP achieves optimal O(m + n) time complexity by utilizing a pre-computed
 * failure function (LPS array) to avoid backtracking in the text.
 * * Time Complexity: O(n + m), where n is the length of the text and m is the length of the pattern.
 * Space Complexity: O(m) to store the failure function.
 */
public final class KMPSearch {

    /**
     * Finds all starting indices in the text where the pattern occurs.
     * * @param text The string to search within (haystack).
     * @param pattern The string to search for (needle).
     * @return A list of starting indices where the pattern is found. Returns an empty list
     * if the pattern is not found or if inputs are invalid.
     */
    public List<Integer> search(String text, String pattern) {
        // Robustness checks for invalid or trivial inputs
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return Collections.emptyList();
        }

        List<Integer> indexList = new ArrayList<>();

        // 1. Pre-processing: Create the Failure Function (LPS array).
        int[] failureFunc = createFailureFunction(pattern);

        int textIndex = 0;   // i: index counter for text (j in many standard notations)
        int patternIndex = 0; // k: index counter for pattern (i in many standard notations)
        int patLen = pattern.length();

        while (textIndex < text.length()) {
            // Case 1: Match found - advance both indices.
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                textIndex++;
                patternIndex++;

                // Case 1a: Full pattern matched.
                if (patternIndex == patLen) {
                    // Pattern found at index (current text index - pattern length).
                    indexList.add(textIndex - patLen);

                    // Shift the pattern using the failure function value.
                    // The next starting match length is given by failureFunc[k-1].
                    patternIndex = failureFunc[patternIndex - 1];
                }
            }
            // Case 2: Mismatch or K=0 after a full match shift.
            else {
                // Case 2a: Pattern has been partially matched (k > 0).
                if (patternIndex != 0) {
                    // Shift the pattern index back using the failure function,
                    // without advancing the text index (i).
                    patternIndex = failureFunc[patternIndex - 1];
                }
                // Case 2b: No characters matched (k = 0).
                else {
                    // Simply advance the text index to the next character.
                    textIndex++;
                }
            }
        }
        return indexList;
    }

    /**
     * Creates the **Failure Function** (also known as the Longest Proper Prefix which is
     * also a Suffix - LPS array). This array tells KMP where to shift the pattern after a mismatch.
     * Time Complexity: O(m), where m is the length of the pattern.
     *
     * @param pattern The pattern string.
     * @return An integer array where func[i] is the length of the longest proper prefix of
     * pattern[0...i] that is also a suffix of pattern[0...i].
     */
    public int[] createFailureFunction(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return new int[0];
        }

        int patLen = pattern.length();
        int[] func = new int[patLen];

        func[0] = 0;
        int patternIndex = 1; // i: iterator pointer starting from the second element
        int matchLength = 0;  // len: length of the previous longest prefix suffix (LPS value)

        while (patternIndex < patLen) {
            // Case 1: Characters match.
            if (pattern.charAt(patternIndex) == pattern.charAt(matchLength)) {
                matchLength++;
                func[patternIndex] = matchLength;
                patternIndex++;
            }
            // Case 2: Mismatch encountered.
            else {
                // Case 2a: The previous LPS length was non-zero.
                if (matchLength != 0) {
                    // Fall back using the previously computed LPS length.
                    // Do NOT increment patternIndex (i); we re-check the current patternIndex.
                    matchLength = func[matchLength - 1];
                }
                // Case 2b: LPS length is zero.
                else {
                    // Start of the pattern does not match; current LPS length is 0.
                    func[patternIndex] = 0;
                    patternIndex++;
                }
            }
        }
        return func;
    }
}