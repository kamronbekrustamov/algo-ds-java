package org.kamronbek.leetcode.easy;

public class RotateString {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}
