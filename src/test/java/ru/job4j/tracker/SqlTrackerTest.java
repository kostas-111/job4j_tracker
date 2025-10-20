package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.entities.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class SqlTrackerTest {
    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenSaveItemsAndDeleteByGeneratedId() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item("itemOne");
        Item itemTwo = new Item("itemTwo");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        assertThat(tracker.findById(itemTwo.getId())).isEqualTo(itemTwo);
        tracker.delete(itemTwo.getId());
        assertThat(tracker.findById(itemTwo.getId())).isNull();
    }

    @Test
    public void whenSaveItemsAndFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item("itemOne");
        Item itemTwo = new Item("itemTwo");
        Item itemThree = new Item("itemThree");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);
        assertThat(tracker.findAll())
                .isNotEmpty()
                .hasSize(3)
                .contains(itemOne)
                .contains(itemTwo)
                .contains(itemThree);
    }

    @Test
    public void whenNoItemsAndFindAllIsEmpty() {
        SqlTracker tracker = new SqlTracker(connection);
        assertThat(tracker.findAll()).isEmpty();
    }

    @Test
    public void whenSaveItemsAndFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item("itemOne");
        Item itemTwo = new Item("ItemDouble");
        Item itemThree = new Item("ItemDouble");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);
        assertThat(tracker.findByName("ItemDouble"))
                .isNotEmpty()
                .hasSize(2)
                .contains(itemTwo)
                .contains(itemThree)
                .doesNotContain(itemOne);
    }

    @Test
    public void whenSaveItemsAndFindByNameIsEmpty() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item("itemOne");
        Item itemTwo = new Item("ItemDouble");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        assertThat(tracker.findByName("ItemSerch")).isEmpty();
    }

    @Test
    public void whenSaveItemAndReplace() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item("itemOne");
        tracker.add(itemOne);
        int idItemOne = itemOne.getId();
        assertThat(tracker.replace(idItemOne, new Item("itemReplace"))).isTrue();
        assertThat(tracker.findByName("itemOne")).isEmpty();
        assertThat(tracker.findById(idItemOne).getName()).isEqualTo("itemReplace");
        assertThat(tracker.findByName("itemReplace")).contains(tracker.findById(idItemOne));
    }

}