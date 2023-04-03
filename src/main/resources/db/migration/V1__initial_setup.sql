drop table if exists degree;

create table degree(id bigint primary key AUTO_INCREMENT, name varchar(255) not null);

INSERT INTO degree(name) values ('Licenciatura en Informatica');
INSERT INTO degree(name) values ('Licenciatura en Bioinformatica');
INSERT INTO degree(name) values ('Licenciatura en Biotecnologia');
INSERT INTO degree(name) values ('Arquitectura Naval');