package org.kamronbek.algo.sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingAlgorithmsTest {

    private final SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
    private final int size = 100000;
    private final int lowerBound = 0;
    private final int upperBound = 100000;

    @Test
    void insertionSort_test() {
        int[] list = sortingAlgorithms.getArrayWithRandomNumbers(size, lowerBound, upperBound);
        sortingAlgorithms.insertionSort(list);
        assertTrue(sortingAlgorithms.isSorted(list));
    }

    @Test
    void bubbleSort_test() {
        int[] list = sortingAlgorithms.getArrayWithRandomNumbers(size, lowerBound, upperBound);
        sortingAlgorithms.bubbleSort(list);
        assertTrue(sortingAlgorithms.isSorted(list));
    }

    @Test
    void selectionSort_test() {
        int[] list = sortingAlgorithms.getArrayWithRandomNumbers(size, lowerBound, upperBound);
        sortingAlgorithms.selectionSort(list);
        assertTrue(sortingAlgorithms.isSorted(list));
    }

    @Test
    void mergeSort_test() {
        int[] list = sortingAlgorithms.getArrayWithRandomNumbers(size, lowerBound, upperBound);
        sortingAlgorithms.mergeSort(list);
        assertTrue(sortingAlgorithms.isSorted(list));
    }

    @Test
    void mergeSortIterative_test() {
        int[] list = sortingAlgorithms.getArrayWithRandomNumbers(size, lowerBound, upperBound);
        sortingAlgorithms.mergeSortIterative(list);
        assertTrue(sortingAlgorithms.isSorted(list));
    }

    @Test
    void quickSort_test() {
        int[] list = sortingAlgorithms.getArrayWithRandomNumbers(size, lowerBound, upperBound);
        sortingAlgorithms.quickSort(list);
        assertTrue(sortingAlgorithms.isSorted(list));
    }

    @Test
    void heapSort_test() {
        int[] list = sortingAlgorithms.getArrayWithRandomNumbers(size, lowerBound, upperBound);
        sortingAlgorithms.heapSort(list);
        assertTrue(sortingAlgorithms.isSorted(list));
    }
}