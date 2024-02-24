package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "Изменить заявку";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Редактирование заявки ===");
        int id = input.askInt("Введите id: ");
        String name = input.askStr("Введите имя: ");
        Item item = new Item(id, name);
        System.out.println(tracker.replace(id, item)
                ? "Заявка изменена успешно." : "Ошибка замены заявки.");
        return true;
    }
}