package dao.daoImpl;

import dao.StudentDao;
import entity.Student;
import utils.ConnectDB;
import utils.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public void add(Student student) {
        String sql = "insert into student values(null, ?, ?, ?)";
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new DbException("数据库连接失败");
        }

        if (student == null) {
            throw new RuntimeException("增加的学生为空");
        }

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, student.getSno());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                student.setId(id);
            } else {
                throw new DbException("增加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
        String sql = "update student set sno = ?, name = ?, age = ? where id = ?";
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new DbException("数据库连接失败");
        }

        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, student.getSno());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setLong(4, student.getId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        if (id <= 0) {
            throw new DbException("输入的id有误");
        }
        String sql = "delete from student where id = ?";
        Connection connection = ConnectDB.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setLong(1, id);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student get(long id) {
        Student student = null;

        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new DbException("数据库连接失败");
        }

        String sql = "select * from student where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();

                student.setId(rs.getLong(1));
                student.setSno(rs.getString(2));
                student.setName(rs.getString(3));
                student.setAge(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> list() {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new DbException("数据库连接失败");
        }
        List<Student> list = new ArrayList<>();
        String sql = "select * from student";
        try (PreparedStatement ps = connection.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong(1));
                student.setSno(rs.getString(2));
                student.setName(rs.getString(3));
                student.setAge(rs.getInt(4));

                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 根据学号、姓名、年龄进行查询
     *
     * @param sno
     * @param name
     * @param age
     * @return
     */
    @Override
    public Student get(String sno, String name, int age) {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new DbException("数据库连接失败");
        }
        Student student = null;
        String sql = "select * from student where sno like ? and name like ? and age = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, sno);
            ps.setString(2, name);
            ps.setInt(3, age);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getLong(1));
                student.setSno(rs.getString(2));
                student.setName(rs.getString(3));
                student.setAge(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}
