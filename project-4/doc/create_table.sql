-- 用户表
create table if not exists `user_test`
(
    `id` bigint not null auto_increment comment 'id' primary key,
    `username` varchar(256) null comment '用户名',
    `password` varchar(256) not null comment '密码',
    `age` int null comment '年龄'
) comment '用户表';

insert into `user_test` (`username`, `password`, `age`) values ('VWmV', 'MiM4o', 3);
insert into `user_test` (`username`, `password`, `age`) values ('Jv7t', 'RSXcK', 1044804);
insert into `user_test` (`username`, `password`, `age`) values ('Rti', 'btjk', 7429927);
insert into `user_test` (`username`, `password`, `age`) values ('ht', 'zo', 76574535);
insert into `user_test` (`username`, `password`, `age`) values ('5X', 'nvx6K', 2998482);
insert into `user_test` (`username`, `password`, `age`) values ('5327V', '3sc', 4127318);
insert into `user_test` (`username`, `password`, `age`) values ('wj9l', 'dszh', 1875966);
insert into `user_test` (`username`, `password`, `age`) values ('Erybp', 'c5', 417045);
insert into `user_test` (`username`, `password`, `age`) values ('Oj', '2L9s', 374541414);
insert into `user_test` (`username`, `password`, `age`) values ('s4ZZI', 'Bi', 65563);