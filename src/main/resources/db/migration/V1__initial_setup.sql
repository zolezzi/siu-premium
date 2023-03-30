drop table if exists USER;

create table USER(id bigint primary key AUTO_INCREMENT, email varchar(255) not null, password varchar(255) not null);

INSERT INTO USER(email, password) values ('cezcardozo23@gmail.com', '$2a$10$LPWV.5AR9qZr2G9tY8liQ.EdX1rephVRYwr3VZqeYg6qTwuuXvK.2');