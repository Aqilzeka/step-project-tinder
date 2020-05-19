-- auto-generated definition
create table users
(
    id       serial  not null
        constraint user_pk
            primary key,
    name     varchar,
    email    varchar not null,
    password varchar not null,
    gender   varchar,
    title    text,
    photo    varchar,
    like_id  integer
);

alter table users
    owner to mgfwnnvgwivxck;

create unique index user_id_uindex
    on users (id);
