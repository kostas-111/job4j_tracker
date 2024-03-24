package ru.job4j.tracker.input;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ru.job4j.tracker.output.*;

import java.util.ArrayList;

class ValidateInputTest {

    @Test
    void whenInvalidInput() {
        Output output = new StubOutput();
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("one");
        inpAnsw.add("1");
        Input in = new MockInput(inpAnsw);
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenValidInput() {
        Output output = new StubOutput();
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("1");
        Input in = new MockInput(inpAnsw);
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenManyValidInput() {
        Output output = new StubOutput();
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("1");
        inpAnsw.add("2");
        inpAnsw.add("5");
        Input in = new MockInput(inpAnsw);
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
        ValidateInput inputTwo = new ValidateInput(output, in);
        int selectedTwo = inputTwo.askInt("Enter menu:");
        assertThat(selectedTwo).isEqualTo(2);
        ValidateInput inputFive = new ValidateInput(output, in);
        int selectedFive = inputFive.askInt("Enter menu:");
        assertThat(selectedFive).isEqualTo(5);
    }

    @Test
    void whenMinusValidInput() {
        Output output = new StubOutput();
        ArrayList<String> inpAnsw = new ArrayList<>();
        inpAnsw.add("-1");
        Input in = new MockInput(inpAnsw);
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(-1);
    }
}