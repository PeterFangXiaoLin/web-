package com.project.model.dao.impl;

import com.project.model.dao.UserDao;
import com.project.model.entity.User;
import com.project.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean add(User user) {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }
        String sql = "insert into user_test values(null, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User user) {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }
        String sql = "update user_test set username = ?, password = ?, age = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setLong(4, user.getId());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        String sql = "delete from user_test where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> list() {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }
        List<User> list = new ArrayList<>();
        String sql = "select * from user_test";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));

                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public User getOne(long id) {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }
        User user = null;
        String sql = "select * from user_test where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public User getOneByUsername(String username) {
        User user = null;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        String sql = "select * from user_test where username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
