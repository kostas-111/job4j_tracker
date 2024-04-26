package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MinDifferenceBetweenNumbersTest {
    @Test
    public void whenDifferenceIsNull() {
        int[] inArray = {5, 3, 1, 6, 1, 3};
        int expected = 0;
        int result = MinDifferenceBetweenNumbers.minDifference(inArray);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenManyNums() {
        int[] inArray = {5, 3, 1, 6, 10, 8};
        int expected = 1;
        int result = MinDifferenceBetweenNumbers.minDifference(inArray);
        assertThat(result).isEqualTo(expected);
    }
}