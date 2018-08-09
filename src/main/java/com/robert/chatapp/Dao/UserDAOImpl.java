package com.robert.chatapp.Dao;

import com.robert.chatapp.Dao.Interfaces.UserDAO;
import com.robert.chatapp.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<User> getUsers() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> query = currentSession.createQuery("from User", User.class);

        return query.getResultList();
    }
}
