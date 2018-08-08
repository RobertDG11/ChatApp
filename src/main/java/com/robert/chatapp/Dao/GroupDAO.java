package com.robert.chatapp.Dao;

import com.robert.chatapp.Entity.Group;
import com.robert.chatapp.Utils.HibernateUtil;
import org.hibernate.*;

@SuppressWarnings("Duplicates")
public class GroupDAO {

    private static GroupDAO instance;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static GroupDAO getInstance() {
        if (instance == null) {
            instance = new GroupDAO();
        }
        return instance;
    }

    private GroupDAO() {

    }

    public static void insertGroup(Group g) {
        Transaction tx = null;

        try(Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(g);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static Group getGroupById(int groupId) {
        Group g = null;

        try (Session session = sessionFactory.openSession()){
            g = session.get(Group.class, groupId);
            Hibernate.initialize(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }

    public static void deleteGroupById(int groupId) {

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Group g = GroupDAO.getGroupById(groupId);
            session.delete(g);
            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public static int getLatestGroupId(){
        Session session = sessionFactory.openSession();
        Group g = (Group) session.createQuery("from Group ORDER BY id DESC")
                .setMaxResults(1).uniqueResult();

        return g.getId();
    }


}
