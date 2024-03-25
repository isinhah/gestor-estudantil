CREATE database faculdadejdbc;

use faculdadejdbc;

CREATE TABLE Major (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE Student (
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
  ('Biology'),
  ('Economics'),
  ('English Literature'),
  ('History'),
  ('Chemistry'),
  ('Physics');


INSERT INTO Student (name, email, major_id) VALUES
  ('João','joao@gmail.com',1),
  ('Ana','ana@gmail.com',2),
  ('Pedro','pedro@gmail.com',3),
  ('Mariana','mariana@gmail.com',4),
  ('Lucas','lucas@gmail.com',5),
  ('Juliana','juliana@gmail.com',1),
  ('Isabel','isabel@gmail.com',1),
  ('Rafaela','rafaela@gmail.com',4),
  ('Gustavo','gustavo@gmail.com',5);
