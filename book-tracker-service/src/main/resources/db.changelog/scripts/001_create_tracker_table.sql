--liquibase formatted sql

--changeset meshicage:1

CREATE TABLE IF NOT EXISTS books_tracking (
    id          SERIAL PRIMARY KEY,
    book_id     BIGINT  NOT NULL UNIQUE,
    is_borrowed BOOLEAN NOT NULL DEFAULT FALSE,
    borrowed_at TIMESTAMP DEFAULT NULL,
    return_by   TIMESTAMP DEFAULT NULL,
    is_deleted  BOOLEAN DEFAULT FALSE,
    is_expired BOOLEAN DEFAULT FALSE
);