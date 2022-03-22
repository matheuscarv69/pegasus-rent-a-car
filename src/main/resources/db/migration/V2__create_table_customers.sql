create table if not exists pegasus.customer
(
    id       bigserial    not null,
    name     varchar(100) not null,
    document varchar(11)  not null,

    primary key (id)
)