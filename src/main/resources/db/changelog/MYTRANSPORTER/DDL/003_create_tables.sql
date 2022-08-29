--liquibase formatted sql
--changeset Antonio Lucian:create-tables-likes

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists likes (
id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
created_at TIMESTAMP NOT NULL,
user_id uuid NOT NULL,
post_id uuid NOT NULL
);


alter table
posts
add likes_id uuid;

alter table
users
add likes_id uuid;


-- User table relationships --

alter table
posts
add
constraint fk_LikesPost FOREIGN KEY (likes_id) REFERENCES likes(id);

alter table
users
add
constraint fk_LikesUser FOREIGN KEY (likes_id) REFERENCES likes(id);

alter table
likes
add
constraint fk_UserLikes FOREIGN KEY (user_id) REFERENCES users(id);

alter table
likes
add
constraint fk_PostLikes FOREIGN KEY (post_id) REFERENCES posts(id);

