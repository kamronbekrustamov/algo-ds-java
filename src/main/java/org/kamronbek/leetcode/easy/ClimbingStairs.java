package org.kamronbek.leetcode.easy;

public class ClimbingStairs {
    public int climbStairs(int n) {
          int first = 1;
          int second = 1;
          int count = 1;
          while (count < n) {
              second = second + first;
              first = second - first;
              count++;
          } 
          return second;
    }
}
