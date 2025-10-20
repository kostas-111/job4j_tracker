package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.entities.Item;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ItemSortTest {
    @Test
    public void whenItemAscByName() {
        List<Item> items = Arrays.asList(
                new Item(2, "Drink"),
                new Item(5, "Eat"),
                new Item(4, "Walk"),
                new Item(1, "Running")
        );
        items.sort(new ItemAscByName());
        List<Item> expected = Arrays.asList(
                new Item(2, "Drink"),
                new Item(5, "Eat"),
                new Item(1, "Running"),
                new Item(4, "Walk")
        );
        assertThat(items).hasSameElementsAs(expected);
    }

    @Test
    void whenItemDescByName() {
        List<Item> items = Arrays.asList(
                new Item(2, "Drink"),
                new Item(5, "Eat"),
                new Item(4, "Walk"),
                new Item(1, "Running")
        );
        items.sort(new ItemAscByName());
        List<Item> expected = Arrays.asList(
                new Item(4, "Walk"),
                new Item(1, "Running"),
                new Item(5, "Eat"),
                new Item(2, "Drink")
        );
        assertThat(items).hasSameElementsAs(expected);
    }
}