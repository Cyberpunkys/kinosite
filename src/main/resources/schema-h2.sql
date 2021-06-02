create table if not exists movie
(
    id          bigint auto_increment primary key,
    name        varchar(255) not null,
    year        int          not null,
    description text,
    poster      varchar(4096)
);
