package ru.job4j.tracker.action;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

public class DeleteAction implements UserAction {
    private final Output output;

    public DeleteAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Удалить заявку";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.println("=== Удаление заявки ===");
        int id = input.askInt("Введите id: ");
        if (tracker.findById(id) != null) {
            tracker.delete(id);
            output.println("Заявка удалена успешно.");
        } else {
            output.println("Ошибка удаления. Заявка с введенным id: " + id + " не найдена.");
        }
        return true;
    }
}