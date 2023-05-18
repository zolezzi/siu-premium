drop table if exists degree;
drop table if exists subject;

create table degree(id bigint primary key AUTO_INCREMENT, name varchar(255) not null);
create table subject(id bigint primary key AUTO_INCREMENT, name varchar(255) not null, is_actived boolean not null default 1, degree_id bigint, constraint fk_subject_degree FOREIGN KEY (degree_id) REFERENCES degree(id));

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