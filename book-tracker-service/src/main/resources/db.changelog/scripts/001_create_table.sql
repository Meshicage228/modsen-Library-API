--liquibase formatted sql

--changeset meshicage:1

CREATE TABLE IF NOT EXISTS books_tracking
(
    id          SERIAL PRIMARY KEY,
    book_id     INT     NOT NULL,
    status      BOOLEAN NOT NULL,
    borrowed_at TIMESTAMP,
    return_by   TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES books (id)
);