package org.kamronbek.algo.string;

import java.util.ArrayList;
import java.util.List;

public class KMPSearch {
    public List<Integer> search(String text, String pattern) {
        List<Integer> indexList = new ArrayList<>();
        // Creating failure function for pattern
        int[] failureFunc = createFailureFunction(pattern);
        int i = 0; // index counter for text
        int k = 0; // index counter for pattern
        int patLen = pattern.length(); // pattern length
        while (i < text.length()) {
            // Check if pattern char and text are the same
            if (text.charAt(i) == pattern.charAt(k)) {
                i++;
                k++;
                if (k == patLen) {
                    // Executes if all chars of pattern matched
                    indexList.add(i - patLen);
                    k = failureFunc[k - 1];
                }
            } else {
                if (k != 0)
                    k = failureFunc[k - 1];
                else
                    i++;
            }
        }
        return indexList;
    }

    public int[] createFailureFunction(String pattern) {
        int[] func = new int[pattern.length()];
        func[0] = 0;
        int i = 1; // iterator pointer
        int len = 0; // matching string length
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                func[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = func[len - 1];
                }
                else {
                    func[i] = 0;
                    i++;
                }
            }
        }
        return func;
    }
}
