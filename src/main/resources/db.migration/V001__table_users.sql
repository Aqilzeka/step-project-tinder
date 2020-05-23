-- auto-generated definition
create table users
(
    id       serial                                    not null
        constraint user_pk
            primary key,
    name     varchar default 'None'::character varying,
    email    varchar                                   not null,
    password varchar                                   not null,
    gender   varchar default 'None'::character varying not null,
    title    text    default 'None'::text,
    photo    text    default 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTUqjY7SU_dEPYWw75ty7pkFPgO_axwUG6s46AOQt_PyXOoX-Y2&usqp=CAU'::character varying
);

alter table users
    owner to gbygpgpuvrardi;

create unique index user_id_uindex
    on users (id);
