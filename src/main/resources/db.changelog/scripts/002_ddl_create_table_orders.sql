create table orders(
    id serial primary key,
    user_id int references users(id) on delete cascade,
    name varchar not null,
    amount double precision,
    status varchar
)