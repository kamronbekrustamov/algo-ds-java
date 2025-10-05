package org.kamronbek.algo.sorting;

import java.util.Random;

public class SortingAlgorithms {

    private static final Random random = new Random();

    /**
     * Sorts the given array using the **Insertion Sort** algorithm.
     * Time Complexity: O(n^2) worst/average case, O(n) best case (if already sorted).
     * Space Complexity: O(1) (in-place).
     *
     * @param list The array of integers to be sorted.
     */
    public static void insertionSort(int[] list) {
        if (list == null || list.length <= 1) {
            return;
        }

        // Iterate from the second element (i=1) to the end.
        for (int i = 1; i < list.length; i++) {
            // j is the index of the element being inserted into the sorted sublist [0...j-1].
            // It correctly uses the early exit condition `else { break; }`.
            for (int j = i; j > 0; j--) {
                if (list[j] < list[j - 1]) {
                    swap(list, j, j - 1);
                } else {
                    // Optimization: Once an element is greater than or equal to
                    // the element before it, the sorted sublist condition is met.
                    break;
                }
            }
        }
    }

    /**
     * Sorts the given array using the **Bubble Sort** algorithm.
     * OPTIMIZATION: Includes an early exit flag to achieve O(n) in the best case.
     * Time Complexity: O(n^2) worst/average case, O(n) best case (if already sorted).
     * Space Complexity: O(1) (in-place).
     *
     * @param list The array of integers to be sorted.
     */
    public static void bubbleSort(int[] list) {
        if (list == null || list.length <= 1) {
            return;
        }

        int n = list.length;
        boolean swapped;

        // Outer loop determines the sorted right-side boundary.
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Reset flag for each pass

            // Inner loop performs the comparisons and swaps (bubbles the largest element to the right).
            // list.length - 1 - i correctly shrinks the comparison range.
            for (int j = 0; j < n - 1 - i; j++) {
                if (list[j] > list[j + 1]) {
                    swap(list, j, j + 1);
                    swapped = true;
                }
            }

            // Optimization: If no two elements were swapped by inner loop, then the array is sorted.
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Sorts the given array using the **Selection Sort** algorithm.
     * This algorithm minimizes swaps (at most n-1 swaps).
     * Time Complexity: O(n^2) worst/average/best case.
     * Space Complexity: O(1) (in-place).
     *
     * @param list The array of integers to be sorted.
     */
    public static void selectionSort(int[] list) {
        if (list == null || list.length <= 1) {
            return;
        }

        int n = list.length;
        // Outer loop determines the left-side boundary of the unsorted subarray.
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // Assume the current element is the smallest.

            // Inner loop finds the index of the minimum element in the remaining unsorted subarray.
            for (int j = i + 1; j < n; j++) {
                if (list[j] < list[minIndex]) {
                    minIndex = j;
                }
            }

            // Only swap if the minimum element is not already at the correct position 'i'.
            if (minIndex != i) {
                swap(list, i, minIndex);
            }
        }
    }


    /**
     * Sorts the entire array of integers using the recursive Merge Sort algorithm.
     *
     * @param list The array of integers to be sorted.
     */
    public void mergeSort(int[] list) {
        if (list == null || list.length <= 1) {
            return; // Handle null or trivial arrays
        }
        mergeSortRecursive(list, 0, list.length - 1);
    }

    /**
     * Recursively divides the array into two halves, sorts them, and then merges them.
     *
     * @param list The array being sorted.
     * @param low The starting index of the subarray (inclusive).
     * @param high The ending index of the subarray (inclusive).
     */
    private void mergeSortRecursive(int[] list, int low, int high) {
        // Base case: The subarray has 0 or 1 element, which is trivially sorted.
        if (low >= high) {
            return;
        }

        // Find the midpoint. (low + high) / 2 is safe from overflow in Java.
        int middle = low + (high - low) / 2; // Alternative calculation for robustness against huge ints

        // Sort left half
        mergeSortRecursive(list, low, middle);

        // Sort right half
        mergeSortRecursive(list, middle + 1, high);

        // Merge the two sorted halves back together
        merge(list, low, middle, high);
    }

    /**
     * Sorts the array using the iterative (bottom-up) Merge Sort algorithm.
     * This version avoids the overhead of recursion but is often less intuitive.
     *
     * @param list The array of integers to be sorted.
     */
    public void mergeSortIterative(int[] list) {
        if (list == null || list.length <= 1) {
            return; // Handle null or trivial arrays
        }

        int length = list.length;
        // 'i' represents the size of the subarrays being merged (1, 2, 4, 8, ...)
        for (int i = 1; i < length; i *= 2) {
            // 'low' is the starting index of the left subarray.
            for (int low = 0; low < length - i; low += 2 * i) {
                // 'mid' is the last index of the left subarray.
                int mid = low + i - 1;

                // 'high' is the last index of the right subarray, ensuring it doesn't exceed the array bounds.
                int high = Math.min(low + 2 * i - 1, length - 1);

                // Merge the two subarrays: [low...mid] and [mid+1...high]
                merge(list, low, mid, high);
            }
        }
    }

    /**
     * Merges two adjacent sorted subarrays back into the original array.
     * The subarrays are [low...middle] and [middle+1...high].
     *
     * @param list The main array containing the two sorted subarrays.
     * @param low The start index of the first subarray.
     * @param middle The end index of the first subarray / middle point.
     * @param high The end index of the second subarray.
     */
    private void merge(int[] list, int low, int middle, int high) {
        // The length of the auxiliary temporary array.
        int tempLength = high - low + 1;
        int[] temp = new int[tempLength];

        // Pointers for the left subarray (i), right subarray (j), and temporary array (k).
        int i = low;        // Start of left subarray
        int j = middle + 1; // Start of right subarray
        int k = 0;          // Start of temp array

        // 1. Merge phase: Copy elements from list[i] and list[j] into temp[k] in sorted order.
        while (i <= middle && j <= high) {
            // Note: The original condition (list[i] > list[j]) results in a stable sort
            // only if the condition is list[i] > list[j]. If it's list[i] >= list[j],
            // the relative order of equal elements is preserved (stable).
            // Here we prioritize stability by moving the right element first on equality.
            if (list[i] > list[j]) {
                temp[k] = list[j];
                j++;
            } else { // list[i] <= list[j] (maintains stability)
                temp[k] = list[i];
                i++;
            }
            k++;
        }

        // 2. Cleanup phase: Copy any remaining elements from the left subarray.
        while (i <= middle) {
            temp[k] = list[i];
            i++;
            k++;
        }

        // 3. Cleanup phase: Copy any remaining elements from the right subarray.
        while (j <= high) {
            temp[k] = list[j];
            j++;
            k++;
        }

        // 4. Optimization: Copy the sorted contents of the temp array back to the original list.
        // The condition (temp.length >= 0) is trivial and can be removed.
        // System.arraycopy is highly optimized (often using native calls).
        System.arraycopy(temp, 0, list, low, tempLength);
    }

    /**
     * Sorts the entire array of integers in ascending order using Quicksort.
     *
     * @param list The array of integers to be sorted.
     */
    public static void quickSort(int[] list) {
        if (list == null || list.length <= 1) {
            return; // Array is null or trivially sorted
        }
        // Start the recursive sort on the whole array (indices 0 to length - 1).
        quickSort(list, 0, list.length - 1);
    }

    /**
     * Recursively sorts the subarray specified by the low and high indices.
     *
     * @param list The array being sorted.
     * @param low The starting index of the subarray (inclusive).
     * @param high The ending index of the subarray (inclusive).
     */
    private static void quickSort(int[] list, int low, int high) {
        // Base case: If low is less than high, there is at least one element to partition.
        if (low < high) {
            // pivot is the partitioning index, list[pivot] is now at the correct sorted position.
            int pivot = partition(list, low, high);

            // Recursively sort elements before the pivot (excluding the pivot itself).
            quickSort(list, low, pivot - 1);

            // Recursively sort elements after the pivot (excluding the pivot itself).
            quickSort(list, pivot + 1, high);
        }
    }

    /**
     * Partitions the subarray around a chosen pivot element (using randomized pivot selection).
     * This places the pivot element at its correct sorted position in the array.
     * All elements smaller than the pivot come before it, and all elements greater come after it.
     *
     * @param list The array being partitioned.
     * @param low The starting index of the subarray.
     * @param high The ending index of the subarray.
     * @return The final index of the pivot element.
     */
    private static int partition(int[] list, int low, int high) {
        // Optimization: Randomized Pivot Selection
        // Moves a randomly chosen element to the 'high' index, which is used as the partitioning element.
        randomPivot(list, low, high);

        // The partitioning element (pivot value) is now list[high].

        // 'i' tracks the index of the last element known to be smaller than the pivot value.
        // It starts one position before the first element (low - 1).
        int i = low - 1;

        // Loop through all elements in the subarray, excluding the pivot element list[high].
        for (int j = low; j < high; j++) {
            // If current element list[j] is smaller than or equal to the pivot value (list[high]).
            if (list[j] <= list[high]) {
                // Increment 'i' to reserve space for the new smaller element.
                i++;
                // Swap list[i] and list[j]. This moves list[j] (the smaller element)
                // into the 'smaller than pivot' section.
                swap(list, i, j);
            }
        }

        // Final swap: Move the pivot element (originally at list[high])
        // to its final sorted position (i + 1).
        swap(list, i + 1, high);

        // Return the final pivot index.
        return i + 1;
    }

    /**
     * Selects a random index between low and high (inclusive) and swaps that element
     * with the element at the high index. This ensures the pivot element (list[high])
     * is chosen randomly, avoiding worst-case O(n^2) performance.
     *
     * @param list The array.
     * @param low The lower bound.
     * @param high The upper bound.
     */
    private static void randomPivot(int[] list, int low, int high) {
        // Use ThreadLocalRandom for efficient and high-quality random number generation.
        // nextInt(origin, bound) returns a random value between origin (inclusive)
        // and bound (exclusive). We use high + 1 for the exclusive upper bound.
        int randIndex = random(low, high + 1);

        // Swap the randomly selected element with the element at the 'high' index.
        swap(list, randIndex, high);
    }


    /**
     * Sorts the given array of integers in ascending order using the Heap Sort algorithm.
     * The process involves two phases:
     * 1. Building a Max Heap from the array (O(n)).
     * 2. Repeatedly extracting the maximum element (root) and restoring the heap property (O(n log n)).
     *
     * @param list The array of integers to be sorted.
     */
    public void heapSort(int[] list) {
        if (list == null || list.length <= 1) {
            return;
        }

        int n = list.length;

        // 1. Build Max Heap: Rearrange array into a Max Heap.
        // Start from the last non-leaf node (index n/2 - 1) and go up to the root (index 0).
        for (int i = n / 2 - 1; i >= 0; i--) {
            // rightBound is n, meaning we consider the entire array
            heapify(list, i, n);
        }

        // 2. Heap Extraction: One by one extract elements from the heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root (the largest element) to the end of the unsorted part (index i)
            swap(list, 0, i);

            // Call heapify on the reduced heap. The rightBound shrinks, as list[i] is now in its final position.
            // The heap size for the next heapify is 'i'.
            heapify(list, 0, i);
        }
    }

    /**
     * Converts a subtree rooted at the given nodeIndex into a Max Heap.
     * This is an **iterative (non-recursive)** implementation, which avoids function call overhead.
     *
     * @param arr The array representing the heap.
     * @param nodeIndex The root index of the subtree to heapify.
     * @param rightBound The exclusive upper boundary (size) of the heap portion currently being considered.
     * Elements at or beyond this index are considered sorted or outside the heap.
     */
    private void heapify(int[] arr, int nodeIndex, int rightBound) {
        int parent = nodeIndex;

        // Use a loop to "sift down" the parent node until the heap property is satisfied.
        while (true) {
            int largest = parent;
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;

            // Find the index of the largest value among parent, left, and right child.

            // Check right child, ensuring it is within the boundary and greater than the current largest.
            if (right < rightBound && arr[right] > arr[largest]) {
                largest = right;
            }

            // Check left child, ensuring it is within the boundary and greater than the current largest.
            // This order is fine as we are comparing against the *current* largest element.
            if (left < rightBound && arr[left] > arr[largest]) {
                largest = left;
            }

            // If the largest element is the parent, the subtree rooted at nodeIndex is a Max Heap, so we stop.
            if (largest == parent) {
                break;
            }

            // Otherwise, swap the parent with the largest child.
            // This is slightly optimized to avoid the repeated array access inside the while loop in the original prompt.
            swap(arr, parent, largest);

            // Move down: the subtree rooted at 'largest' now needs re-heapifying.
            parent = largest;
        }
    }

    /**
     * Generates an array of integers filled with pseudo-random numbers within a specified range.
     * The random numbers are uniformly distributed.
     *
     * Time Complexity: O(n), where n is the size of the array, as it iterates through the array once.
     * Space Complexity: O(n), to store the resulting array.
     *
     * @param size The desired size (length) of the array. Must be 1 or greater.
     * @param lowerBound The smallest possible random value (inclusive).
     * @param upperBound The exclusive upper limit (the returned value will be strictly less than this).
     * @return A newly allocated array of size 'size' containing random integers.
     * @throws IllegalArgumentException if size is less than 1 or if lowerBound is greater than or equal to upperBound.
     */
    public int[] getArrayWithRandomNumbers(int size, int lowerBound, int upperBound) {
        // 1. Robustness Check: Validate the size.
        if (size < 1) {
            throw new IllegalArgumentException("Array size must be 1 or greater. Provided size: " + size);
        }

        // 2. Robustness Check: Validate the range, ensuring the bounds are meaningful.
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Lower bound (" + lowerBound + ") must be strictly less than the upper bound (" + upperBound + ").");
        }

        int[] list = new int[size];

        // 3. Populate the array (O(n)).
        // Optimized: Direct assignment within the loop is cleaner than using a 'temp' variable.
        for (int i = 0; i < list.length; i++) {
            // Calls the external random number generation function.
            // It is assumed to be implemented as: returnRandom(lowerBound, upperBound)
            list[i] = random(lowerBound, upperBound);
        }

        return list;
    }

    /**
     * Swaps the elements at two specified indices in an array.
     *
     * @param arr The array.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Checks if an array of integers is sorted in **non-decreasing** (ascending) order.
     * An array is considered sorted if list[i-1] is less than or equal to list[i]
     * for all valid indices i.
     *
     * This check runs in O(n) time, where n is the length of the list,
     * and O(1) space, as it performs a single pass through the array.
     *
     * @param list The array of integers to check.
     * @return {@code true} if the array is sorted, {@code false} otherwise.
     */
    public static boolean isSorted(int[] list) {
        // 1. Handle edge cases: A null array is conventionally considered not sorted
        // or an error, while an empty or single-element array is always sorted.
        if (list == null) {
            // Depending on requirements, could throw an IllegalArgumentException,
            // but returning false is often safer.
            return false;
        }

        // An array with 0 or 1 element is always sorted.
        if (list.length <= 1) {
            return true;
        }

        // 2. Linear scan (O(n)): Iterate through the array starting from the second element (index 1).
        for (int i = 1; i < list.length; i++) {
            // If the current element is strictly less than the previous element,
            // the non-decreasing order is violated.
            if (list[i - 1] > list[i]) {
                return false; // Immediately exit upon finding the first unsorted pair
            }
        }

        // 3. Conclusion: If the loop completes without returning false, the array is sorted.
        return true;
    }

    /**
     * Returns a pseudo-random, uniformly distributed integer between a specified lower bound (inclusive)
     * and an upper bound (exclusive).
     * * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param lowerBound The smallest possible return value (inclusive).
     * @param upperBound The exclusive upper limit (the returned value will be strictly less than this).
     * @return A random integer in the range [lowerBound, upperBound).
     * @throws IllegalArgumentException if lowerBound is greater than or equal to upperBound.
     */
    public static int random(int lowerBound, int upperBound) {
        // Robustness Check: Ensure the range is valid.
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Lower bound (" + lowerBound + ") must be strictly less than upper bound (" + upperBound + ").");
        }

        // Calculate the size of the range (bound - origin).
        int range = upperBound - lowerBound;

        // Shift the resulting random number (0 to range-1) by adding the lowerBound.
        return random.nextInt(range) + lowerBound;
    }
}

