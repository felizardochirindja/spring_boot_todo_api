create table users(
    id int not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    constraint pk_users primary key (id)
) default character set = utf8mb4 default collate  utf8mb4_0900_ai_ci;