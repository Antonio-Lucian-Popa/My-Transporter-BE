--liquibase formatted sql
--changeset Antonio Lucian:create-tables

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists users (
id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(250) NOT NULL,
birthday DATE NOT NULL,
user_role VARCHAR(50) NOT NULL,
address_id uuid NOT NULL,
post_id uuid,
image_id uuid,
followers_id uuid,
followed_id uuid
);


create table if not exists address (
id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
country VARCHAR(50) NOT NULL,
city VARCHAR(50) NOT NULL,
county VARCHAR(250),
user_id uuid,
post_id uuid
);

create table if not exists posts (
id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
title VARCHAR(50) NOT NULL,
description TEXT NOT NULL,
created_at TIMESTAMP NOT NULL,
address_id uuid,
user_id uuid NOT NULL,
image_id uuid NOT NULL
);

create table if not exists images (
id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
value TEXT NOT NULL,
created_at TIMESTAMP NOT NULL,
post_id uuid,
user_id uuid
);


create table if not exists notifications (
id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
title VARCHAR(20) NOT NULL,
description text NOT NULL,
created_at TIMESTAMP NOT NULL,
notification_action_id uuid NOT NULL,
post_id uuid NOT NULL
);

create table if not exists notification_actions (
id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
is_read boolean NOT NULL,
is_new boolean NOT NULL,
notification_id uuid NOT NULL,
user_id uuid NOT NULL
);


-- User table relationships --

alter table
users
add
constraint fk_PostUser FOREIGN KEY (post_id) REFERENCES posts(id);

alter table
users
add
constraint fk_AddressUser FOREIGN KEY (address_id) REFERENCES address(id);

alter table
users
add
constraint fk_ImageUser FOREIGN KEY (image_id) REFERENCES images(id);

alter table
users
add
constraint fk_FollowersUser FOREIGN KEY (followers_id) REFERENCES users(id);

alter table
users
add
constraint fk_FollowedUser FOREIGN KEY (followed_id) REFERENCES users(id);


-- Address table relationships --

alter table
address
add
constraint fk_UserAddress FOREIGN KEY (user_id) REFERENCES users(id);

alter table
address
add
constraint fk_PostAddress FOREIGN KEY (post_id) REFERENCES posts(id);

-- Post table relationships --

alter table
posts
add
constraint fk_AddressPost FOREIGN KEY (address_id) REFERENCES address(id);

alter table
posts
add
constraint fk_AddressUser FOREIGN KEY (user_id) REFERENCES users(id);

alter table
posts
add
constraint fk_AddressImage FOREIGN KEY (image_id) REFERENCES images(id);

-- Images table relationships --

alter table
images
add
constraint fk_ImagePost FOREIGN KEY (post_id) REFERENCES posts(id);

alter table
images
add
constraint fk_ImageUser FOREIGN KEY (user_id) REFERENCES users(id);


-- Notification table relationships --

alter table
notifications
add
constraint fk_NotificationActionNotification FOREIGN KEY (notification_action_id) REFERENCES notification_actions(id);

alter table
notifications
add
constraint fk_PostNotification FOREIGN KEY (post_id) REFERENCES posts(id);


-- Notification Action table relationships --

alter table
notification_actions
add
constraint fk_NotificationNotificationAction FOREIGN KEY (notification_id) REFERENCES notifications(id);

alter table
notification_actions
add
constraint fk_UserNotificationAction FOREIGN KEY (user_id) REFERENCES users(id);
