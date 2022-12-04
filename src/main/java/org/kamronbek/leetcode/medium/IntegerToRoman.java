package org.kamronbek.leetcode.medium;

public class IntegerToRoman {
    public String intToRoman(int num) {
        String[][] roman = {{"", "I", "II", "III","IV", "V","VI", "VII","VIII", "IX"},
                            {"", "X", "XX", "XXX","XL", "L","LX", "LXX","LXXX", "XC"},
                            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                            {"", "M", "MM", "MMM"}};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            res.insert(0, roman[i][num % 10]);
            num /= 10;
        }
        return res.toString();
    }
}
