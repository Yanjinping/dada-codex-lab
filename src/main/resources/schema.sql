create table if not exists user (
    id bigint primary key auto_increment,
    phone varchar(20) not null,
    nickname varchar(50),
    create_time datetime not null
);
