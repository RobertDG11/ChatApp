package com.robert.chatapp.Dao;

import com.robert.chatapp.Entity.Notification;
import com.robert.chatapp.Utils.HibernateUtil;
import org.hibernate.*;

@SuppressWarnings("Duplicates")
public class NotificationDAO {

    private static NotificationDAO instance;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static NotificationDAO getInstance() {
        if (instance == null) {
            instance = new NotificationDAO();
        }
        return instance;
    }

    private NotificationDAO() {

    }

    public static void insertNotification(Notification notification) {

        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(notification);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static Notification getNotificationById(int notificationId) {

        Notification notification = null;

        try (Session session = sessionFactory.openSession()) {
            notification = session.get(Notification.class, notificationId);
            Hibernate.initialize(notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notification;
    }

    public static void deleteNotificationById(int notificationId) {

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Notification n = NotificationDAO.getNotificationById(notificationId);
            session.delete(n);
            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

}
