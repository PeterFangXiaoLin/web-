package com.project.model.dao;

import com.project.model.entity.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    void delete(long id);

    List<User> list();

    User getOneByAccount(String account);
}
