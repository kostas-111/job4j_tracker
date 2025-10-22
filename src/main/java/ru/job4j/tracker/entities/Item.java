package ru.job4j.tracker.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String name;

    private LocalDateTime created = LocalDateTime.now();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    /*
    name - указывает на таблицу, где идет связь вторичных ключей.
    joinColumns - определяет ключ родительского объекта. В данном примере Item.id
    inverseJoinColumns - определяет ключ объекта, который мы загружаем в родительский объект.
     */
    @ManyToMany
    @JoinTable(
        name = "participates",
        joinColumns = { @JoinColumn(name = "item_id") },
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> participates = new ArrayList<>();

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public Item() {
    }

    /*
    Переопределяем toString вручную, так как нужно кастомное форматирование даты
     */
    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", created=" + created.format(FORMATTER)
                + '}';
    }

    /*
    Переопределяем equals и hashCode вручную,
    они не должны учитывать поле created
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}