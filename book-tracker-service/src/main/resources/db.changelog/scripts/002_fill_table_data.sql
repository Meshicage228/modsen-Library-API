--liquibase formatted sql

--changeset meshicage:2

INSERT INTO books_tracking (book_id, is_borrowed, borrowed_at, return_by)
VALUES (1, TRUE, '2025-01-01 10:00:00', '2025-01-07 10:00:00'),
       (2, TRUE, '2025-01-01 12:30:00', '2025-01-07 12:30:00'),
       (3, FALSE, NULL, NULL),
       (4, FALSE, NULL, NULL),
       (5, FALSE, NULL, NULL);