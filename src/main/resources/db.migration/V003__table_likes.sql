-- auto-generated definition
create table likes
(
    id        serial not null
        constraint likes_pk
            primary key,
    user_from integer,
    user_to   integer
);

alter table likes
    owner to mgfwnnvgwivxck;

create unique index likes_id_uindex
    on likes (id);

