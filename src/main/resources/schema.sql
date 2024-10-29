create table if not exists student (
    id int primary key auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    phone_number varchar(255) not null
);

create table if not exists users (
    username varchar(50) primary key,
    password varchar(255) not null,
    enabled boolean not null
);

create table if not exists authorities (
    username varchar(50),
    authority varchar(50),
    constraint fk_authorities_users foreign key(username) references users(username)
);
--insert into users (username, password, enabled) values {('anusha', '$2a$12$2l2RFw5X7LimlvjDWNqPwefQVpLwUIt5eg9sZe5WHurwJ706c.Z7a', 1`true), ('john', '$2a$12$0cg8VqaXhfS8VsfTswgLTOUBXtqYVFmpE9tN8p7SPn6qiMqVrEJ4i', true)};
insert into users (username, password, enabled) values
('anusha', '$2a$12$2l2RFw5X7LimlvjDWNqPwefQVpLwUIt5eg9sZe5WHurwJ706c.Z7a', true),
('john', '$2a$12$0cg8VqaXhfS8VsfTswgLTOUBXtqYVFmpE9tN8p7SPn6qiMqVrEJ4i', true);
insert into authorities (username, authority) values ('anusha', 'ADMIN'), ('john', 'USER');