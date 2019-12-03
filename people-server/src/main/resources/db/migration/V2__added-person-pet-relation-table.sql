create table person_pets(
  person_id int not null references person(id),
  pets_id int not null references pet(id)
);
