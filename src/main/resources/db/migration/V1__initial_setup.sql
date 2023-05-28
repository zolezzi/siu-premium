drop table if exists committee_accounts_students;
drop table if exists committee_accounts_professor;
drop table if exists account;
drop table if exists user;
drop table if exists committee;
drop table if exists semester_degree_subject;
drop table if exists subject;
drop table if exists degree;

create table account(id bigint primary key AUTO_INCREMENT, account_role varchar(255) not null, dni varchar(255) not null, firstname varchar(255) not null, lastname varchar(255) not null );
create table user(id bigint primary key AUTO_INCREMENT, email varchar(255) not null, password varchar(255) not null, account_id bigint, constraint fk_user_account FOREIGN KEY (account_id) REFERENCES account(id));
create table degree(id bigint primary key AUTO_INCREMENT, name varchar(255) not null);
create table subject(id bigint primary key AUTO_INCREMENT, name varchar(255) not null, is_actived boolean not null default 1, degree_id bigint, constraint fk_subject_degree FOREIGN KEY (degree_id) REFERENCES degree(id));


INSERT INTO account(account_role, dni, firstname, lastname) VALUES ('ADMIN', '36001205', 'ADMIN', 'UNQ-PREMIUM');
INSERT INTO user (email, password, account_id) VALUES ('adminunqpremium@gmail.com', 'SG9sYVBsYW5ldGExMSE=', 1);

INSERT INTO degree(name) values ('Licenciatura en Informatica');
INSERT INTO degree(name) values ('Licenciatura en Bioinformatica');
INSERT INTO degree(name) values ('Licenciatura en Biotecnologia');
INSERT INTO degree(name) values ('Arquitectura Naval');

INSERT INTO subject(name, is_actived, degree_id) values ('Introduccion a la Programacion', 1, 1);
INSERT INTO subject(name, is_actived, degree_id) values ('Organizacion de Computadoras', 1, 1);
INSERT INTO subject(name, is_actived, degree_id) values ('Matematica I', 1, 1);

INSERT INTO subject(name, is_actived, degree_id) values ('Quimica I', 1, 2);
INSERT INTO subject(name, is_actived, degree_id) values ('Biología General', 1, 2);
INSERT INTO subject(name, is_actived, degree_id) values ('Algebra y Geometria Analitica', 1, 2);
INSERT INTO subject(name, is_actived, degree_id) values ('Analisis Matematico I', 1, 2);

INSERT INTO subject(name, is_actived, degree_id) values ('Fisica I', 1, 3);
INSERT INTO subject(name, is_actived, degree_id) values ('Quimica I', 1, 3);
INSERT INTO subject(name, is_actived, degree_id) values ('Diseño estadistico de experimentos ', 1, 3);

INSERT INTO subject(name, is_actived, degree_id) values ('Estabilidad I	', 1, 4);
INSERT INTO subject(name, is_actived, degree_id) values ('Diseño Asistido', 1, 4);
INSERT INTO subject(name, is_actived, degree_id) values ('Mecanica de Fluidos', 1, 4);