CREATE TABLE localidades (
	id INT NOT NULL,
	nombre VARCHAR(200) NOT NULL,
	provincia_id INT,
	PRIMARY KEY(id)
);
INSERT INTO localidades (id, nombre, provincia_id) VALUES (1,'ROSARIO',1);
INSERT INTO localidades (id, nombre, provincia_id) VALUES (2,'CABA',2);
INSERT INTO localidades (id, nombre, provincia_id) VALUES (3,'SANTA FE',1);
INSERT INTO localidades (id, nombre, provincia_id) VALUES (4,'CORDOBA',3);