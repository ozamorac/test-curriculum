DROP TABLE IF EXISTS persona ;

CREATE TABLE persona  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(8) NOT NULL,
  apellido VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL
);

CREATE TABLE experiecia (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_persona INT NOT NULL,
  empresa varchar(150) NOT NULL ,
  cargo varchar(150) NOT NULL ,
  descripcion varchar(200) NOT NULL,
  fechas varchar(200) NOT NULL
);

CREATE TABLE habilidad (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_persona INT NOT NULL,
  nombre varchar(150) NOT NULL ,
  nivel varchar(150) NOT NULL 
 
); 

CREATE TABLE estudio (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_persona INT NOT NULL,
  estudio varchar(150) NOT NULL ,
  descripcion varchar(150) NOT NULL
) ;