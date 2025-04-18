SET FOREIGN_KEY_CHECKS=0;
drop table if exists tasks;
SET FOREIGN_KEY_CHECKS=1;

create table if not exists tasks(
    id int not null auto_increment,
    title varchar(255) not null,
    status enum('PENDING', 'COMPLETED') not null,
    user_id int not null,
    constraint pk_tasks primary key (id),
    constraint fk_tasks_users foreign key (user_id) references users(id)
) engine=innodb default character set = utf8mb4 collate utf8mb4_0900_ai_ci;
