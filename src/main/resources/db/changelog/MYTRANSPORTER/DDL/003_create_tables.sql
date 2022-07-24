--liquibase formatted sql
--changeset Antonio Lucian:create-tables-invitation-link

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

alter table
posts
add likes_id uuid;


-- User table relationships --

alter table
posts
add
constraint fk_LikesPost FOREIGN KEY (likes_id) REFERENCES users(id);

