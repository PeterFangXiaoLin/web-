package com.project.model.dao.daoImpl;

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
    public void add(User user) {
        if (user == null) {
            throw new RuntimeException("请求参数错误");
        }

        String sql = "insert into user values(null, ?, ?)";
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassword());

            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new RuntimeException("添加失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new RuntimeException("请求参数错误");
        }
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        String sql = "update user set account = ?, password = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassword());

            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new RuntimeException("更新失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        if (id <= 0) {
            throw new RuntimeException("请求参数为空");
        }

        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        String sql = "delete from user where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new RuntimeException("删除失败");
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

        String sql = "select * from user";
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setAccount(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));

                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public User getOneByAccount(String account) {
        Connection connection = ConnectDB.getConnection();
        User user = null;
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }
        String sql = "select * from user where account = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, account);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setAccount(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
