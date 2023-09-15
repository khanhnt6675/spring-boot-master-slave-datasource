-- liquibase formatted sql
-- changeset khanhnt:1.0

CREATE TABLE IF NOT EXISTS user
(
    id        INT AUTO_INCREMENT
        PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    CONSTRAINT user_username_uindex
        UNIQUE (username)
);

