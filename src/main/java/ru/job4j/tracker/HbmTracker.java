package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.entities.Item;

import java.util.Collections;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(HbmTracker.class);

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Item result = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            result = item;
        } catch (Exception e) {
            LOG.error("Ошибка при добавлении заявки: {}", item, e);
        }
        return result;
    }

    @Override
    public boolean replace(int id, Item item) {
        int updated = 0;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            updated = session.createQuery(
                    "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Ошибка при изменении заявки с id: {}", id, e);
        }
        return updated > 0;
    }

    @Override
    public void delete(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery(
                    "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Ошибка при удалении заявки с id: {}", id, e);
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = Collections.emptyList();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from Item", Item.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Ошибка поиска всех заявок", e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = Collections.emptyList();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery(
                    "from Item as i where i.name = :fName", Item.class)
                    .setParameter("fName", key)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Ошибка при поиске заявки с наименованием: {}", key, e);
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery(
                            "from Item as i where i.id = :fId", Item.class)
                    .setParameter("fId", id)
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Ошибка при поиске заявки по id: {}", id, e);
        }
        return result;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}