create table account
(
    id             int auto_increment
        primary key,
    user_name      varchar(100) null,
    age            int          null,
    birthday       datetime     null,
    account_status varchar(12)  null
);

create table user
(
    id          bigint      not null comment '主键ID'
        primary key,
    name        varchar(30) null comment '姓名',
    age         int         null comment '年龄',
    email       varchar(50) null comment '邮箱',
    create_id   bigint      null,
    create_time datetime    null,
    update_id   bigint      null,
    update_time datetime    null,
    user_type   varchar(16) null,
    sex         int         null
);

