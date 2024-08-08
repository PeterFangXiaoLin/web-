use homework;

-- 用户表
create table if not exists `user`
(
    `id` bigint not null auto_increment comment 'id' primary key,
    `account` varchar(256) not null comment '账号',
    `password` varchar(256) not null comment '密码'
) comment '用户表';

insert into `user` (`account`, `password`) values ('uUVR', '123456');
insert into `user` (`account`, `password`) values ('Iihlv', '123456');
insert into `user` (`account`, `password`) values ('GUdm', '123456');
insert into `user` (`account`, `password`) values ('lDlIG', '123456');
insert into `user` (`account`, `password`) values ('qETFm', '123456');
insert into `user` (`account`, `password`) values ('os2Ms', '123456');
insert into `user` (`account`, `password`) values ('vt', '123456');
insert into `user` (`account`, `password`) values ('jqxn', '123456');
insert into `user` (`account`, `password`) values ('tk', '123456');
insert into `user` (`account`, `password`) values ('22Ix', '123456');

