create database my_user;

drop table student;

-- 学生表
create table if not exists `student`
(
    `id` bigint not null auto_increment comment 'id' primary key,
    `sno` varchar(256) not null comment '学号',
    `name` varchar(256) not null comment '姓名',
    `age` int null comment '年龄'
) comment '学生表';

insert into `student` (`sno`, `name`, `age`) values ('101', '陶建辉', 20);
insert into `student` (`sno`, `name`, `age`) values ('102', '秦胤祥', 20);
insert into `student` (`sno`, `name`, `age`) values ('103', '曾弘文', 20);
insert into `student` (`sno`, `name`, `age`) values ('104', '卢擎宇', 20);
insert into `student` (`sno`, `name`, `age`) values ('105', '白明轩', 20);
insert into `student` (`sno`, `name`, `age`) values ('106', '沈鸿煊', 20);
insert into `student` (`sno`, `name`, `age`) values ('107', '钱熠彤', 20);
insert into `student` (`sno`, `name`, `age`) values ('108', '韦胤祥', 20);
insert into `student` (`sno`, `name`, `age`) values ('109', '夏智渊', 20);
insert into `student` (`sno`, `name`, `age`) values ('110', '毛耀杰', 20);