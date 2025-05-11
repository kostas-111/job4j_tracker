package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(HbmTracker.class);

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            return item;
        } catch (Exception e) {
            LOG.error("Ошибка при добавлении заявки: {}", item, e);
            throw new RuntimeException("Ошибка при добавлении заявки", e);
        }
    }

    @Override
    public boolean replace(int id, Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            int updated = session.createQuery(
                    "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return updated > 0;
        } catch (Exception e) {
            LOG.error("Ошибка при изменении заявки с id: {}", id, e);
            throw new RuntimeException("Ошибка при изменении заявки", e);
        }
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
            throw new RuntimeException("Ошибка при удалении заявки", e);
        }
    }

    @Override
    public List<Item> findAll() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Item> result = session.createQuery("from Item", Item.class).list();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            LOG.error("Ошибка поиска всех заявок", e);
            throw new RuntimeException("Ошибка поиска всех заявок", e);
        }
    }

    @Override
    public List<Item> findByName(String key) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Item> result = session.createQuery(
                    "from Item as i where i.name = :fName", Item.class)
                    .setParameter("fName", key)
                    .list();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            LOG.error("Ошибка при поиске заявки с наименованием: {}", key, e);
            throw new RuntimeException("Ошибка при поиске заявке по ниаменованию", e);
        }
    }

    @Override
    public Item findById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Item result = session.createQuery(
                            "from Item as i where i.id = :fId", Item.class)
                    .setParameter("fId", id)
                    .uniqueResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            LOG.error("Ошибка при поиске заявки по id: {}", id, e);
            throw new RuntimeException("Ошибка при поиске заявки по id", e);
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}