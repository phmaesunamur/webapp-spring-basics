CREATE TABLE t_author (
  id       INT PRIMARY KEY NOT NULL,
  username VARCHAR(30)     NOT NULL,
  password VARCHAR(64)     NOT NULL
);

CREATE SEQUENCE author_pk_seq
  START 1
  INCREMENT 1
  NO MAXVALUE
  CACHE 1;

CREATE TABLE t_todo (
  id        INT PRIMARY KEY NOT NULL,
  content   VARCHAR(512)    NOT NULL,
  author_id INT             NOT NULL REFERENCES t_author(id),
  done      BOOLEAN         DEFAULT FALSE
);

CREATE SEQUENCE todo_pk_seq
  START 1
  INCREMENT 1
  NO MAXVALUE
  CACHE 1;
