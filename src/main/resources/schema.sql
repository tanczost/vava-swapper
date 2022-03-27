DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS photos;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS product_offers;

CREATE TABLE users
(
    id         serial PRIMARY KEY,
    nick       varchar(255) UNIQUE NOT NULL,
    first_name varchar(255)        NOT NULL,
    last_name  varchar(255)        NOT NULL,
    email      varchar(255) UNIQUE NOT NULL,
    password   varchar(255)        NOT NULL,
    town       varchar(255)        NOT NULL,
    street     varchar(255)        NOT NULL,
    school     varchar(255)        NOT NULL,
    created_at TIMESTAMP           NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS photos
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(200) NOT NULL,
    phone_number INTEGER      NOT NULL,
    uploader_id  INTEGER      NOT NULL,
    data         BYTEA        NOT NULL
);

CREATE TABLE products
(
    id          serial PRIMARY KEY,
    name        varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    topped      boolean default false,
    img_id      int,
    user_id     int,
    FOREIGN KEY (img_id) REFERENCES photos (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE product_offers
(
    id          serial PRIMARY KEY,
    proposal_id int NOT NULL,
    offer_id    int NOT NULL,
    FOREIGN KEY (proposal_id) REFERENCES products (id),
    FOREIGN KEY (offer_id) REFERENCES products (id)
)



