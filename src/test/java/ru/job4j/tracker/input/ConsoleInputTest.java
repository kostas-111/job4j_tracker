package ru.job4j.tracker.input;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.action.ReplaceAction;
import ru.job4j.tracker.entities.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

class ConsoleInputTest {

  @Test
  public void whenItemWasReplacedSuccessfully() {
    Output output = new StubOutput();
    MemTracker tracker = new MemTracker();
    tracker.add(new Item("Replaced item"));
    String replacedName = "New item name";
    ReplaceAction replaceAction = new ReplaceAction(output);

    Input input = mock(Input.class);

    when(input.askInt(any(String.class))).thenReturn(1);
    when(input.askStr(any(String.class))).thenReturn(replacedName);

    replaceAction.execute(input, tracker);

    String ln = System.lineSeparator();
    assertThat(output.toString()).isEqualTo(
        "=== Редактирование заявки ===" + ln
            + "Заявка изменена успешно." + ln
    );
  }

}