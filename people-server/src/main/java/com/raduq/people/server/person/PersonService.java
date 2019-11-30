package com.raduq.people.server.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public Person getPerson(Long id) {
		return repository.findById(id)
			.map(PersonEntity::toDTO)
			.orElseThrow(() -> new PersonNotFoundException(id));
	}

	public List<Person> getPeople(String firstName, String lastName) {
		return repository.findByFilters(firstName, lastName)
			.stream()
			.map(PersonEntity::toDTO)
			.collect(Collectors.toList());
	}

	public Person save(Person person) {
		return repository.save(person.toEntity()).toDTO();
	}

	public Person update(Long id, Person updatePerson) {
		repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		return repository.save(updatePerson.toEntity()).toDTO();
	}

	public void delete(Long id) {
		PersonEntity person = repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		repository.delete(person);
	}
}
