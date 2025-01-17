--liquibase formatted sql

--changeset meshicage:1

CREATE TABLE IF NOT EXISTS roles (
    id   SERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id       SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    role_id  INT,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);