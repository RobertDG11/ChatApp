package com.robert.chatapp.Dao;

import com.robert.chatapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    @Query("select u from User u")
    @Transactional
    List<User> getUsers();
}
