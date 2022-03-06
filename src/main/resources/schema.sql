CREATE TABLE friends (
     id SERIAL PRIMARY KEY,
     name VARCHAR(200) NOT NULL,
     phone_number INTEGER NOT NULL
);

INSERT INTO friends  (name, phone_number) VALUES ('Tomi', 12321);