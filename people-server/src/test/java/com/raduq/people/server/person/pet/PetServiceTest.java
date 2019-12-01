package com.raduq.people.server.person.pet;

import com.raduq.people.server.TestInstances;
import com.raduq.people.server.person.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

	@Mock
	private PetRepository repository;
	@Mock
	private PersonService personService;
	@InjectMocks
	private PetService service;

	private TestInstances testInstances = new TestInstances();

	@Test
	@DisplayName("Should get pet by id")
	public void shouldGetById() {
		when(repository.findById(1L))
			.thenReturn(Optional.of(testInstances.petEntity()));

		PetEntity pet = service.getPet(1L);

		assertEquals(pet, testInstances.petEntity());
	}

	@Test
	@DisplayName("Should get all pets")
	public void shouldGetAll() {
		when(repository.findAll())
			.thenReturn(Collections.singletonList(testInstances.petEntity()));

		Iterable<PetEntity> pets = service.getPets();

		assertEquals(pets.iterator().next(), testInstances.petEntity());
	}

	@Test
	@DisplayName("Should create pet")
	public void shouldCreatePet() {
		PetEntity entity = testInstances.petEntity();
		when(personService.getPerson(1L)).thenReturn(testInstances.personEntity());
		when(repository.save(entity)).thenReturn(entity);

		PetEntity saved = service.save(1L, testInstances.pet());

		assertEquals(saved, testInstances.petEntity());
	}

	@Test
	@DisplayName("Should update pet")
	public void shouldUpdatePet() {
		PetEntity entity = testInstances.petEntity();
		when(personService.getPerson(1L)).thenReturn(testInstances.personEntity());
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(entity);

		PetEntity updated = service.update(1L, 1L, testInstances.pet());

		assertEquals(updated, testInstances.petEntity());
	}

	@Test
	@DisplayName("Should not update pet when not exists")
	public void shouldNotUpdatePetWhenNotFound() {
		Pet pet = testInstances.pet();
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(PetNotFoundException.class, () -> service.update(1L, 1L, pet));
	}

	@Test
	@DisplayName("Should delete pet")
	public void shouldDeletePet() {
		PetEntity entity = testInstances.petEntity();
		when(repository.findById(1L)).thenReturn(Optional.of(testInstances.petEntity()));
		when(personService.getPerson(1L)).thenReturn(testInstances.personEntity());
		doNothing().when(repository).delete(entity);

		service.delete(1L, 1L);

		verify(repository).delete(entity);
	}

	@Test
	@DisplayName("Should not delete pet when not exists")
	public void shouldNotDeleteWhenPetNotFound() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(PetNotFoundException.class, () -> service.delete(1L, 1L));
	}

}
