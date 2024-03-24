package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.MockInput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class StartUITest {

    @Test
    void whenCreateItem() {
        Output output = new StubOutput();
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add("Item name");
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        Tracker tracker = new Tracker();
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    void whenReplaceItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add(String.valueOf(item.getId()));
        inpAnsw.add(replacedName);
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenDeleteItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add(String.valueOf(item.getId()));
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    void whenExit() {
        Output output = new StubOutput();
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        Input input = new MockInput(inpAnsw);
        Tracker tracker = new Tracker();
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString()).isEqualTo(
                "Меню:" + System.lineSeparator()
                        + "0. Завершить программу" + System.lineSeparator()
                        + "=== Завершение программы ===" + System.lineSeparator()
        );
    }

    @Test
    void whenReplaceItemTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add(String.valueOf(one.getId()));
        inpAnsw.add(replaceName);
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
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
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindAllAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
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
        Tracker tracker = new Tracker();
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindAllAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
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
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        String findName = "test2";
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add(findName);
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByNameAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
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
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        String findName = "test100";
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add(findName);
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByNameAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
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
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("0");
        inpAnsw.add(String.valueOf(one.getId()));
        inpAnsw.add("1");
        Input input = new MockInput(inpAnsw);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByIdAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
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
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("7");
        inpAnsw.add("0");
        Input input = new MockInput(inpAnsw);
        Tracker tracker = new Tracker();
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
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
}