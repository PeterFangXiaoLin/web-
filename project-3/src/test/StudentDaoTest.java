package test;

import dao.StudentDao;
import dao.daoImpl.StudentDaoImpl;
import entity.Student;

import java.util.List;
import java.util.Scanner;

public class StudentDaoTest {

    private static StudentDao studentDao = new StudentDaoImpl();

    public static void main(String[] args) {

        do {
            System.out.println("-----学生管理系统-------");
            System.out.println("-    1. 添加学生      -");
            System.out.println("-    2. 删除学生      -");
            System.out.println("-    3. 修改学生      -");
            System.out.println("-    4. 根据学生id查询 -");
            System.out.println("-    5. 根据学生学号、姓名、年龄查询学生 ");
            System.out.println("-    6. 查询所有学生   -");
            System.out.println("-    0. 输入 0 退出   -");


            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();

            if (input == 0) {
                break;
            }
            if (input == 1) {
                add();
            }

            if (input == 2) {
                delete();
            }

            if (input == 3) {
                update();
            }

            if (input == 4) {
                selectById();
            }

            if (input == 5) {
                select();
            }

            if (input == 6) {
                list();
            }
        } while (true);

    }

    private static void add() {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入学号：");
        String sno = sc.next();

        System.out.print("请输入姓名：");
        String name = sc.next();

        System.out.print("请输入年龄：");
        int age = sc.nextInt();

        Student student = new Student();
        student.setSno(sno);
        student.setName(name);
        student.setAge(age);

        studentDao.add(student);
        if (student.getId() > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    private static void delete() {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入要删除的学生ID：");
        long id = sc.nextLong();

        studentDao.delete(id);

        System.out.println("删除成功");
    }

    private static void update() {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入要更新的学生id：");
        long id = sc.nextLong();

        System.out.print("请输入新的学号：");
        String sno = sc.next();

        System.out.print("请输入新的姓名：");
        String name = sc.next();

        System.out.print("请输入新的年龄：");
        int age = sc.nextInt();

        Student student = new Student();
        student.setId(id);
        student.setSno(sno);
        student.setName(name);
        student.setAge(age);

        studentDao.update(student);
    }

    private static void selectById() {
        Scanner sc = new Scanner(System.in);
        long id = sc.nextLong();

        Student student = studentDao.get(id);
        if (student == null) {
            System.out.println("查无该学生");
        } else {
            System.out.println("查到了，信息如下：");
            System.out.println(student);
        }
    }

    private static void select() {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入学号：");
        String sno = sc.next();

        System.out.print("请输入姓名：");
        String name = sc.next();

        System.out.print("请输入年龄：");
        int age = sc.nextInt();

        Student student = studentDao.get(sno, name, age);
        if (student == null) {
            System.out.println("查无该学生");
        } else {
            System.out.println("查到了， 学生信息如下：");
            System.out.println(student);
        }
    }

    private static void list() {
        List<Student> list = studentDao.list();
        for (Student student: list) {
            System.out.println(student);
        }
    }
}
