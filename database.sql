CREATE database estudantesjdbc;

use estudantesjdbc;

CREATE TABLE Major (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE Students (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  major_id INT,
  FOREIGN KEY (major_id) REFERENCES Major(id)
);

INSERT INTO Major (name) VALUES
  ('Computer Science'),
  ('Psychology'),
  ('Mechanical Engineering'),
  ('Medicine'),
  ('Biology');

INSERT INTO Students (name, email,  major_id) VALUES
  ('Bob Brown','bob@gmail.com',1),
  ('Maria Green','maria@gmail.com',2),
  ('Alex Grey','alex@gmail.com',3),
  ('Martha Red','martha@gmail.com',4),
  ('Donald Blue','donald@gmail.com',5);
