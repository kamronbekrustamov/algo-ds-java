package org.kamronbek.leetcode.easy;

public class RotatedDigits {

    public int rotatedDigits(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isGoodNumber(i))
                count++;
        }
        return count;
    }

    public boolean isGoodNumber(int num) {

        char[] digits = Integer.toString(num).toCharArray();

        for (int i = 0; i < digits.length; i++) {
            char c = digits[i];
            if (c == '3' || c == '4' || c == '7')
                return false;
            if (c == '2')
                digits[i] = '5';
            else if (c == '5')
                digits[i] = '2';
            else if (c == '6')
                digits[i] = '9';
            else if (c == '9')
                digits[i] = '6';
        }
        return num != Integer.parseInt(String.copyValueOf(digits));
    }

}
