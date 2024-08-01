

CREATE TABLE IF NOT EXISTS mascota (
  id INT AUTO_INCREMENT PRIMARY KEY, 
  name VARCHAR (255) not null,
  fecha_nac DATE not null,
  RAZA varchar (255) not null,
  peso DOUBLE not null,
  tiene_chip BOOLEAN not null,
  url_foto VARCHAR(255) not null
);


