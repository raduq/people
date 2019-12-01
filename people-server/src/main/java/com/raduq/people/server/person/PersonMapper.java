package com.raduq.people.server.person;

import com.raduq.people.server.person.address.Address;
import com.raduq.people.server.person.address.AddressEntity;
import com.raduq.people.server.person.pet.Pet;
import com.raduq.people.server.person.pet.PetEntity;

import java.util.Collection;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Optional.ofNullable;

public final class PersonMapper {

	public final PersonEntity toEntity(Person person) {
		return PersonEntity.builder()
			.id(person.getId())
			.firstName(person.getFirstName())
			.lastName(person.getLastName())
			.birthDate(person.getBirthDate())
			.address(addressToEntity(person))
			.pets(petsToEntity(person))
			.build();
	}

	public final Person toDTO(PersonEntity person) {
		return Person.builder()
			.id(person.getId())
			.firstName(person.getFirstName())
			.lastName(person.getLastName())
			.birthDate(person.getBirthDate())
			.address(addressToDTO(person))
			.pets(petsToDTO(person))
			.build();
	}

	public final List<Person> toListOfDTO(Spliterator<PersonEntity> entities) {
		return StreamSupport.stream(entities, false)
			.map(PersonEntity::toDTO)
			.collect(Collectors.toList());
	}

	private List<PetEntity> petsToEntity(Person person) {
		return ofNullable(person.getPets())
			.stream()
			.flatMap(Collection::stream)
			.map(Pet::toEntity)
			.collect(Collectors.toList());
	}

	private AddressEntity addressToEntity(Person person) {
		return ofNullable(person.getAddress()).map(Address::toEntity).orElse(null);
	}

	private List<Pet> petsToDTO(PersonEntity person) {
		return ofNullable(person.getPets())
			.stream()
			.flatMap(Collection::stream)
			.map(PetEntity::toDTO)
			.collect(Collectors.toList());
	}

	private Address addressToDTO(PersonEntity person) {
		return ofNullable(person.getAddress()).map(AddressEntity::toDTO).orElse(null);
	}
}
