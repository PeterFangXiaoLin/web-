package com.project.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    // 增加
    public void add(User user) {
        String sql = "insert into user_test values(null, ?, ?, ?)";
        Connection connection =ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        if (user == null) {
            throw new RuntimeException("增加的用户为空");
        }

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                user.setId(id);
            } else {
                throw new RuntimeException("增加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新
    public void update(User user) {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        if (user == null) {
            throw new RuntimeException("user为空");
        }

        String sql = "update user_test set username = ?, password = ?, age = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());
            ps.setLong(4, user.getId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 删除
    public void delete(long id) {
        if (id <= 0) {
            throw new RuntimeException("输入的id有误");
        }
        String sql = "delete from user_test where id = ?";
        Connection connection = ConnectDB.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setLong(1, id);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据id查询
    public User getOne(long id) {
        if (id <= 0) {
            throw new RuntimeException("用户id非法");
        }

        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        User user = null;
        String sql = "select * from user_test where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                user = new User();

                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 查询全部
    public List<User> list() {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }

        String sql = "select * from user_test";
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));

                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
