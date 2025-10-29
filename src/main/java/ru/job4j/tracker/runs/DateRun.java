package ru.job4j.tracker.runs;

import java.time.LocalDateTime;
import java.util.TimeZone;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.entities.Item;

public class DateRun {
  public static void main(String[] args) {
    var registry = new StandardServiceRegistryBuilder()
        .configure().build();
    try (var sf = new MetadataSources(registry)
        .buildMetadata().buildSessionFactory()) {
      var session = sf.openSession();
      session.beginTransaction();
      var item = new Item();
      item.setName("check timezone");
      item.setCreated(LocalDateTime.now());
      session.persist(item);
      session.getTransaction().commit();
      session.close();
    }  catch (Exception e) {
      e.printStackTrace();
    } finally {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
}
