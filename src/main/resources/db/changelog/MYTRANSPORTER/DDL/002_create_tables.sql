--liquibase formatted sql
--changeset Antonio Lucian:create-tables-invitation-link

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

alter table
users
add invitation_link_id uuid;


create table if not exists invitation_links(
id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
token VARCHAR(250) NOT NULL,
created_at TIMESTAMP NOT NULL,
expired_at TIMESTAMP NOT NULL
);


-- User table relationships --

alter table
users
add
constraint fk_InvitationLinkUser FOREIGN KEY (invitation_link_id) REFERENCES invitation_links(id);

