DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id serial PRIMARY KEY ,
                       nick varchar(255) UNIQUE NOT NULL,
                       first_name varchar(255) NOT NULL,
                       last_name varchar(255) NOT NULL,
                       email varchar(255) UNIQUE NOT NULL,
                       password varchar(255) NOT NULL,
                       town varchar(255) NOT NULL ,
                       street varchar(255) NOT NULL ,
                       school varchar(255) NOT NULL ,
                       created_at TIMESTAMP NOT NULL
);

ALTER TABLE users ALTER COLUMN created_at SET DEFAULT now();