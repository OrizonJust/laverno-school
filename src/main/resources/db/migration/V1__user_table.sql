create extension if not exists "uuid-ossp";

create schema if not exists school;

create table school.sc_t_user
(
    usr_id       uuid primary key default public.uuid_generate_v4(),
    usr_name     varchar(70) not null,
    usr_password varchar(50) not null,
    usr_email    varchar(50) not null,
    usr_phone    varchar(30),
    usr_avatar   varchar(100),
    usr_disabled boolean          default false
);