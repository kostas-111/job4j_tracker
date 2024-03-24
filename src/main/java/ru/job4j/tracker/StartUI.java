package ru.job4j.tracker;

import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.output.ConsoleOutput;
import ru.job4j.tracker.output.Output;

import java.util.ArrayList;

public class StartUI {
    private final Output output;

    public  StartUI(Output output) {
        this.output = output;
    }

    private void showMenu(ArrayList<UserAction> actions) {
        output.println("Меню:");
        for (UserAction act : actions) {
            output.println(actions.indexOf(act) + ". " +  act.name());
        }
    }

    public void init(Input input, Tracker tracker, ArrayList<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Выбрать: ");
            if (select < 0 || select >= actions.size()) {
                output.println("Неверный ввод, вы можете выбрать: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Tracker tracker = new Tracker();
        ArrayList<UserAction> actions = new ArrayList<>();
                actions.add(new CreateAction(output));
                actions.add(new FindAllAction(output));
                actions.add(new ReplaceAction(output));
                actions.add(new DeleteAction(output));
                actions.add(new FindByIdAction(output));
                actions.add(new FindByNameAction(output));
                actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
    }
}