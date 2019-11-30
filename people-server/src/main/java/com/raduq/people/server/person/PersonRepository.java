package com.raduq.people.server.person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
