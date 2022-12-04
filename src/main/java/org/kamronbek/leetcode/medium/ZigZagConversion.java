package org.kamronbek.leetcode.medium;


public class ZigZagConversion {
    public static String convert(String s, int numRows) {

        if (numRows == 1) return s;
        StringBuilder result = new StringBuilder(s.length());
        int length = s.length();
        int cycleLen = 2 * numRows - 2;
        
        for(int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < length; j += cycleLen) {
                result.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < length)
                    result.append(s.charAt(j + cycleLen - i));
            }
        }

        return result.toString();
    }
}