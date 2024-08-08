package dao;

import entity.Student;

import java.util.List;

public interface StudentDao {
    // 增加
    void add(Student student);

    // 修改
    void update(Student student);

    // 删除
    void delete(long id);

    // 获取
    Student get(long id);

    // 有三个查询参数的示例
    Student get(String sno, String name, int age);

    // 获取所有数据
    List<Student> list();
}
