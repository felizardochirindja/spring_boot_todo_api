create table if not exists users(
    id int not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    constraint pk_users primary key (id),
    unique index unq_email (email asc) visible
) engine=innodb default character set = utf8mb4 collate  utf8mb4_0900_ai_ci;
