--liquibase formatted sql

--changeset meshicage:1

CREATE TABLE IF NOT EXISTS genres (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS books (
    id          SERIAL PRIMARY KEY,
    isbn        VARCHAR(20)  NOT NULL UNIQUE,
    title       VARCHAR(255) NOT NULL,
    genre_id    INT          NOT NULL,
    description TEXT,
    author      VARCHAR(100) NOT NULL,
    is_deleted  BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS storage_logs (
    id         SERIAL PRIMARY KEY,
    table_name TEXT NOT NULL,
    operation  TEXT NOT NULL,
    old_data   JSONB,
    new_data   JSONB,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);