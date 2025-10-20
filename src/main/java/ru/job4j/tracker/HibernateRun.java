package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.entities.Item;
import ru.job4j.tracker.entities.Role;
import ru.job4j.tracker.entities.User;
import ru.job4j.tracker.entities.UserMessenger;

import java.util.List;

public class HibernateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            var role = new Role();
            role.setName("ADMIN");
            create(role, sf);
            var user = new User();
            user.setName("Admin Admin");
            user.setMessengers(List.of(
                new UserMessenger("tg", "@tg"),
                new UserMessenger("wu", "@wu")
            ));
            user.setRole(role);
            create(user, sf);
            var stored = sf.openSession()
                .createQuery("FROM User WHERE id = :fId", User.class)
                .setParameter("fId", user.getId())
                .getSingleResult();
            stored.getMessengers().forEach(System.out::println);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> void create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.persist(model);
        session.getTransaction().commit();
        session.close();
    }

    public static <T> List<T> findAll(Class<T> cl, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<T> list = session.createQuery("FROM " + cl.getName(), cl).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}