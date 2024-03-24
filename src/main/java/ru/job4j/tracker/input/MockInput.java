package ru.job4j.tracker.input;

import java.util.ArrayList;

public class MockInput implements Input {
    private ArrayList<String> answers;
    private int position = 0;

    public MockInput(ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override
    public String askStr(String question) {
        return answers.get(position++);
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }
}