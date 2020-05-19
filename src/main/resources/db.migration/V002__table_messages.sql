-- auto-generated definition
create table messages
(
    id         serial not null
        constraint messages_pk
            primary key,
    user_to    integer,
    user_from  integer,
    local_id   integer,
    my_message text,
    date_time  varchar
);

alter table messages
    owner to mgfwnnvgwivxck;

create unique index messages_id_uindex
    on messages (id);