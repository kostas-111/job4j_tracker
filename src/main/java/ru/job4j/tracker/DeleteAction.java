package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "Удалить заявку";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Удаление заявки ===");
        int id = input.askInt("Введите id: ");
        System.out.println(tracker.delete(id)
                ? "Заявка удалена успешно." : "Ошибка удаления заявки.");
        return true;
    }
}