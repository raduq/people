create table address(
  id int auto_increment primary key,
  street varchar(100) not null,
  zip_code varchar(100) not null,
  number int,
  number_addition int,
  city varchar(100),
  country varchar(100)
);

create table pet(
  id int auto_increment primary key,
  name varchar(100) not null,
  age int
);

create table person(
  id int auto_increment primary key,
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  birth_date timestamp,
  address_id int,
  CONSTRAINT ctx_firstname_lastname UNIQUE (first_name, last_name),
  foreign key(address_id) references address(id)
);


