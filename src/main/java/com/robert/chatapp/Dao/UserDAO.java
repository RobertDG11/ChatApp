package com.robert.chatapp.Dao;

import com.robert.chatapp.Entity.User;
import com.robert.chatapp.Utils.HibernateUtil;
import org.hibernate.*;

@SuppressWarnings("Duplicates")
public class UserDAO {

    private static UserDAO instance;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    private UserDAO() {

    }

    public static void insertUser(User user) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static User getUserById(int userId) {
        User user = null;

        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, userId);
            Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void deleteUserById(int userId) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User u = UserDAO.getUserById(userId);
            session.delete(u);
            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public static int getLatestUserId(){
        Session session = sessionFactory.openSession();
        User u = (User) session.createQuery("from User ORDER BY id DESC")
                .setMaxResults(1).uniqueResult();

        return u.getId();
    }


}
