package org.kamronbek.algo.string;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KMPSearchTest {
    private final KMPSearch kmpSearch = new KMPSearch();

    @Test
    void kmpSearch_test1() {
        String text = "this is a sample text used";
        String pattern = "text";
        List<Integer> result = kmpSearch.search(text, pattern);
        assertEquals(1, result.size());
        assertEquals(17, result.get(0));
    }

    @Test
    void kmpSearch_test2() {
        String text = "this is a sample text used text";
        String pattern = "text";
        List<Integer> result = kmpSearch.search(text, pattern);
        assertEquals(2, result.size());
        assertEquals(17, result.get(0));
        assertEquals(27, result.get(1));
    }

    @Test
    void kmpSearch_test3() {
        String text = "this is a sample used";
        String pattern = "text";
        List<Integer> result = kmpSearch.search(text, pattern);
        assertEquals(0, result.size());
    }
}
