package com.raduq.people.server.person;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

	@Query(value = "SELECT * FROM person p WHERE 1 = 1 " +
		"AND p.first_name = ?1 OR p.last_name = ?2", nativeQuery = true)
	List<PersonEntity> findByFilters(String firstName, String lastName);

}
