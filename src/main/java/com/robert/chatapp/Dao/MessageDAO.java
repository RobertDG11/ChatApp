package com.robert.chatapp.Dao;

import com.robert.chatapp.Entity.Message;
import com.robert.chatapp.Utils.HibernateUtil;
import org.hibernate.*;

@SuppressWarnings("Duplicates")
public class MessageDAO {

    private static MessageDAO instance;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static MessageDAO getInstance() {
        if (instance == null) {
            instance = new MessageDAO();
        }
        return instance;
    }

    private MessageDAO() {

    }

    public static void insertMessage(Message message) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(message);
            session.flush();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static Message getMessageById(int messageId) {
        Message message = null;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            message = session.get(Message.class, messageId);
            Hibernate.initialize(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public static void deleteMessageById(int messageId) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Message message = MessageDAO.getMessageById(messageId);
            session.delete(message);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public static int getLatestMessageId(){
        Session session = sessionFactory.openSession();
        Message m = null;

        try {
            session.beginTransaction();
            m = (Message) session.createQuery("from Message ORDER BY id DESC")
                    .setMaxResults(1).uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e) {
            session.close();
        }

        return m.getId();
    }

}
