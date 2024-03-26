create table customer(
    id BIGSERIAL primary key,
    name text not null,
    email text not null,
    age int not null
);