create schema if not exists people;
create table people.address(
  id int auto_increment primary key,
  street varchar not null,
  zip_code varchar not null,
  number bigint,
  number_addition bigint,
  city varchar,
  country varchar
);

create table people.pet(
  id int auto_increment primary key,
  name varchar not null,
  age int
);

create table people.person(
  id int auto_increment primary key,
  first_name varchar not null,
  last_name varchar not null,
  birth_date timestamp,
  address_id int
);

alter table people.person add foreign key(address_id) references people.address(id);
alter table people.person add constraint ctx_firstname_lastname UNIQUE(first_name, last_name);


