SET FOREIGN_KEY_CHECKS=0;
drop table if exists users;
SET FOREIGN_KEY_CHECKS=1;

create table if not exists users(
    id int not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    role_id int not null,
    constraint pk_users primary key (id),
    constraint fk_users_roles foreign key (role_id) references roles(id),
    unique index unq_email (email asc) visible
) engine=innodb default character set = utf8mb4 collate  utf8mb4_0900_ai_ci;
