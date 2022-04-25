DROP TABLE IF EXISTS product_offers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS photos;
DROP TABLE IF EXISTS users;

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
    is_admin   boolean                      DEFAULT FALSE,
    created_at TIMESTAMP           NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS photos
(
    id   SERIAL PRIMARY KEY,
    data BYTEA NOT NULL
);


CREATE TABLE products
(
    id          serial PRIMARY KEY,
    name        varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    topped      boolean   default false,
    img_id      int NOT NULL,
    user_id     int NOT NULL ,
    category    varchar(255) NOT NULL,
    created_at  timestamp default CURRENT_TIMESTAMP,
    FOREIGN KEY (img_id) REFERENCES photos (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE product_offers
(
    id          serial PRIMARY KEY,
    proposal_id int NOT NULL,
    offer_id    int NOT NULL,
    FOREIGN KEY (proposal_id) REFERENCES products (id),
    FOREIGN KEY (offer_id) REFERENCES products (id),
    UNIQUE (proposal_id, offer_id)
);


