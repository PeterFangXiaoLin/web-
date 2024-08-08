package com.project.model.dao;

import com.project.model.entity.User;

import java.util.List;

public interface UserDao {
    boolean add(User user);

    boolean update(User user);

    boolean delete(long id);

    List<User> list();

    User getOne(long id);

    User getOneByUsername(String username);
}
