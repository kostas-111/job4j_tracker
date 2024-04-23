package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class UniqueNumbersTest {
    @Test
    public void whenDuplicatesInArray() {
        int[] arrayNum = {3, 4, 10, 3, 4, 5};
        Set<Integer> result = UniqueNumbers.unigueNumFromArray(arrayNum);
        List<Integer> expected = Arrays.asList(10, 5);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void wheNoDuplicatesInArray() {
        int[] arrayNum = {3, 4, 5};
        Set<Integer> result = UniqueNumbers.unigueNumFromArray(arrayNum);
        List<Integer> expected = Arrays.asList(3, 4, 5);
        assertThat(result).containsAll(expected);
    }
}