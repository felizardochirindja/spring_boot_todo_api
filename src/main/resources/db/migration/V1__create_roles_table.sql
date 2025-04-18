SET FOREIGN_KEY_CHECKS=0;
drop table if exists roles;
SET FOREIGN_KEY_CHECKS=1;

create table roles(
    id int not null auto_increment,
    name varchar(255) not null,
    description mediumtext,
    constraint pk_roles primary key (id),
    unique index unq_name (name asc) visible
) engine=innodb default character set utf8mb4 collate utf8mb4_0900_ai_ci;