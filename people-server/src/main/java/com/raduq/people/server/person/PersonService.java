package com.raduq.people.server.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public PersonEntity getPerson(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new PersonNotFoundException(id));
	}

	public Iterable<PersonEntity> getPeople() {
		return repository.findAll();
	}

	public Iterable<PersonEntity> getPeople(String firstName, String lastName) {
		if (Objects.isNull(firstName) && Objects.isNull(lastName)) {
			return getPeople();
		}
		return repository.findByFilters(firstName, lastName);
	}

	public PersonEntity save(Person person) {
		return repository.save(person.toEntity());
	}

	public PersonEntity update(Long id, Person updatePerson) {
		PersonEntity foundEntity = getPerson(id);
		PersonEntity personToUpdate = updatePerson.toEntity();
		personToUpdate.setId(foundEntity.getId());
		return repository.save(personToUpdate);
	}

	public void delete(Long id) {
		PersonEntity person = getPerson(id);
		repository.delete(person);
	}
}
