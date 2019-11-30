package com.raduq.people.server.person.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PetService {

	@Autowired
	private PetRepository repository;

	public Pet getPet(Long id) {
		return repository.findById(id)
			.map(PetEntity::toDTO)
			.orElseThrow(() -> new PetNotFoundException(id));
	}

	public List<Pet> getPets() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
			.map(PetEntity::toDTO)
			.collect(Collectors.toList());
	}

	public Pet save(Pet pet) {
		return repository.save(pet.toEntity()).toDTO();
	}

	public Pet update(Long id, Pet pet) {
		repository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
		return repository.save(pet.toEntity()).toDTO();
	}

	public void delete(Long id) {
		PetEntity pet = repository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
		repository.delete(pet);
	}
}
