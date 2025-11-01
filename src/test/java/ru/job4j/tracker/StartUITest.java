package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.*;
import ru.job4j.tracker.entities.Item;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.MockInput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ru.job4j.tracker.runs.StartUI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StartUITest {

    @Test
    void whenCreateItem() {
        Output output = new StubOutput();
        Input input = new MockInput(
                Arrays.asList("0", "Item name", "1")
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new CreateAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    void whenReplaceItem() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input input = new MockInput(
                Arrays.asList("0", String.valueOf(item.getId()), replacedName, "1")
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenDeleteItem() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                Arrays.asList("0", String.valueOf(item.getId()), "1")
        );
        List<UserAction> actions = Arrays.asList(
                new DeleteAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        assertThat(memTracker.findById(item.getId())).isNull();
    }

    @Test
    void whenExit() {
        Output output = new StubOutput();
        Input input = new MockInput(
            List.of("0")
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = List.of(
            new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        assertThat(output.toString()).isEqualTo(
                "Меню:" + System.lineSeparator()
                        + "0. Завершить программу" + System.lineSeparator()
                        + "=== Завершение программы ===" + System.lineSeparator()
        );
    }

    @Test
    void whenReplaceItemTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input input = new MockInput(
                Arrays.asList("0", String.valueOf(one.getId()), replaceName, "1")
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Редактирование заявки ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindAllActionTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Item two = memTracker.add(new Item("test2"));
        Input input = new MockInput(
                Arrays.asList("0", "1")
        );
        List<UserAction> actions = Arrays.asList(
                new FindAllAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод всех заявок ===" + ln
                        + one + ln
                        + two + ln
                        + "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindAllActionTestOutputIsFailed() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Input input = new MockInput(
                Arrays.asList("0", "1")
        );
        List<UserAction> actions = Arrays.asList(
                new FindAllAction(output),
                new ExitAction(output));
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод всех заявок ===" + ln
                        + "Хранилище еще не содержит заявок" + ln
                        + "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByNameActionTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Item two = memTracker.add(new Item("test2"));
        String findName = "test2";
        Input input = new MockInput(
                Arrays.asList("0", findName, "1")
        );
        List<UserAction> actions = Arrays.asList(
                new FindByNameAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявок по имени ===" + ln
                        + two + ln
                        + "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByNameActionTestOutputIsFailed() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Item two = memTracker.add(new Item("test2"));
        String findName = "test100";
        List<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add(findName);
        inpAnsw.add("1");
        Input input = new MockInput(
                Arrays.asList("0", findName, "1")
        );
        List<UserAction> actions = Arrays.asList(
                new FindByNameAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявок по имени ===" + ln
                        + "Заявки с именем: " + findName + " не найдены." + ln
                        + "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByIdActionTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Item two = memTracker.add(new Item("test2"));
        Input input = new MockInput(
                Arrays.asList("0", String.valueOf(one.getId()), "1")
        );
        List<UserAction> actions = Arrays.asList(
                new FindByIdAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявки по id ===" + ln
                        + one + ln
                        + "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenInvalidExit() {
        Output output = new StubOutput();
        Input input = new MockInput(
                Arrays.asList("7", "0"));
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = List.of(
            new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "Неверный ввод, вы можете выбрать: 0 .. 0" + ln
                        + "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenDeleteItemWithMockito() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleted item"));

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(0, item.getId(), 1);

        List<UserAction> actions = Arrays.asList(
            new DeleteAction(output),
            new ExitAction(output)
        );

        new StartUI(output).init(input, memTracker, actions);

        assertThat(memTracker.findById(item.getId())).isNull();
    }

    @Test
    void whenDeleteItemIsFailedWithMockito() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        one.setId(1);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(0, 999, 1);

        List<UserAction> actions = Arrays.asList(
            new DeleteAction(output),
            new ExitAction(output)
        );

        new StartUI(output).init(input, memTracker, actions);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
            "Меню:" + ln
                + "0. Удалить заявку" + ln
                + "1. Завершить программу" + ln
                + "=== Удаление заявки ===" + ln
                + "Ошибка удаления. Заявка с введенным id: " + 999 + " не найдена." + ln
                + "Меню:" + ln
                + "0. Удалить заявку" + ln
                + "1. Завершить программу" + ln
                + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByNameActionTestOutputIsSuccessfullyWithMockito() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Item two = memTracker.add(new Item("test2"));
        String findName = "test2";

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(0, 1);
        when(input.askStr(any(String.class))).thenReturn(findName);

        List<UserAction> actions = Arrays.asList(
            new FindByNameAction(output),
            new ExitAction(output)
        );

        new StartUI(output).init(input, memTracker, actions);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
            "Меню:" + ln
                + "0. Показать заявки по имени" + ln
                + "1. Завершить программу" + ln
                + "=== Вывод заявок по имени ===" + ln
                + two + ln
                + "Меню:" + ln
                + "0. Показать заявки по имени" + ln
                + "1. Завершить программу" + ln
                + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByNameActionTestOutputIsFailedWithMockito() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Item two = memTracker.add(new Item("test2"));
        String findName = "test100";

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(0, 1);
        when(input.askStr(any(String.class))).thenReturn(findName);

        List<UserAction> actions = Arrays.asList(
            new FindByNameAction(output),
            new ExitAction(output)
        );

        new StartUI(output).init(input, memTracker, actions);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
            "Меню:" + ln
                + "0. Показать заявки по имени" + ln
                + "1. Завершить программу" + ln
                + "=== Вывод заявок по имени ===" + ln
                + "Заявки с именем: " + findName + " не найдены." + ln
                + "Меню:" + ln
                + "0. Показать заявки по имени" + ln
                + "1. Завершить программу" + ln
                + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByIdActionTestOutputIsSuccessfullyWithMockito() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Item two = memTracker.add(new Item("test2"));

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(0, one.getId(), 1);

        List<UserAction> actions = Arrays.asList(
            new FindByIdAction(output),
            new ExitAction(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
            "Меню:" + ln
                + "0. Показать заявку по id" + ln
                + "1. Завершить программу" + ln
                + "=== Вывод заявки по id ===" + ln
                + one + ln
                + "Меню:" + ln
                + "0. Показать заявку по id" + ln
                + "1. Завершить программу" + ln
                + "=== Завершение программы ===" + ln
        );
    }
}