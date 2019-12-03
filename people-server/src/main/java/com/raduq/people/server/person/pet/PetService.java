package com.raduq.people.server.person.pet;

import com.raduq.people.server.person.PersonEntity;
import com.raduq.people.server.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

	@Autowired
	private PetRepository repository;
	@Autowired
	private PersonService personService;

	public PetEntity getPet(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new PetNotFoundException(id));
	}

	public Iterable<PetEntity> getPets() {
		return repository.findAll();
	}

	public PetEntity save(Long personId, Pet pet) {
		PersonEntity person = personService.getPerson(personId);
		person.addPet(pet.toEntity());
		personService.update(person.getId(), person.toDTO());
		return repository.save(pet.toEntity());
	}

	public PetEntity update(Long personId, Long id, Pet pet) {
		PetEntity oldPet = getPet(id);
		PersonEntity person = personService.getPerson(personId);
		person = person.putPet(oldPet, pet.toEntity());
		personService.update(person.getId(), person.toDTO());
		return pet.toEntity();
	}

	public void delete(Long personId, Long id) {
		PetEntity pet = getPet(id);
		PersonEntity personEntity = personService.getPerson(personId);
		personEntity.getPets().remove(pet);
		repository.delete(pet);
	}
}
