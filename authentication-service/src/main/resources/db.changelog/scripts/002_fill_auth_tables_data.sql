--liquibase formatted sql

--changeset meshicage:2

INSERT INTO roles (role)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO users (username, password, role_id)
VALUES ('Vlad', '$2a$10$bubPP66FrlTdC8BXF0rcrehfqZr5X8PjTHS5M1pJQFBquIWp7lWua', 1), -- PASS IS 111111
       ('Lolita', '$2a$10$3iIpTr6TyZ8b7ELsmn90au4/uqIsOSR6/i5PCaNr7/lo2dK2qXxke', 2); -- PASS IS 123123