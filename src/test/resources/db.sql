CREATE SCHEMA MySchema;

CREATE TABLE user(
  id INT IDENTITY PRIMARY KEY,
  name VARCHAR(256) NOT NULL
);