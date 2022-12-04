package org.kamronbek.backtracking;


import java.util.ArrayList;

public class SumOfSubsets {

    // The given set
    public static int[] set = {5, 10, 12, 13, 15, 18};
    // The array used to check which values are included
    public static boolean[] isIncluded = new boolean[set.length];
    // The target value
    public static int target = 30;
    // Used to save such subsets
    public static ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();

    public static void main(String[] args) {
        findSubsets(0, 0);
        displaySubSets();
    }

    // Used to find all the subsets
    public static boolean findSubsets(int index, int sum) {
        // Serves as a stopping point.
        // index == set.length means sum was not enough and it is asking next value
        // if so it should stop
        if (index == set.length)
            return false;

        // adding the current value to sum
        // and making it included
        sum += set[index];
        isIncluded[index] = true;

        // checking if sum is equal to target
        // if so saving the current state and continuing
        if (sum == target) {
            saveCurrentState();
        }

        // checking if sum is less than target
        // if so calling findSubsets() for next value
        if (sum < target && findSubsets(index + 1, sum))
            return true;

        // if sum is not less than target or findSubsets() returns false
        // remove the value from sum and make it not included
        isIncluded[index] = false;
        sum -= set[index];

        // calling findSubsets() and current value is not included in sum
        if(findSubsets(index + 1, sum))
            return true;

        // the steps above were unsuccessful
        return false;
    }

    // used to save the current subset
    public static void saveCurrentState() {
        ArrayList<Integer> subset = new ArrayList<>();
        for (int i = 0; i < set.length; i++)
            if (isIncluded[i])
                subset.add(set[i]);
        subsets.add(subset);
    }

    // used to display all the subsets or indicates there is no one
    public static void displaySubSets() {
        if (subsets.isEmpty())
            System.out.println("There is no such subset");

        for (ArrayList<Integer> subset: subsets)
            System.out.println(subset);
    }
}

