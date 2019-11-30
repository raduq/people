package com.raduq.people.server.person;

import com.raduq.people.server.TestInstances;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository repository;
	@InjectMocks
	private PersonService service;

	private TestInstances testInstances = new TestInstances();

	@Test
	@DisplayName("Should get person by id")
	public void shouldGetById() {
		when(repository.findById(1L))
			.thenReturn(Optional.of(testInstances.personEntity()));

		Person person = service.getPerson(1L);

		assertEquals(person, testInstances.person());
	}

	@Test
	@DisplayName("Should get all people")
	public void shouldGetAll() {
		when(repository.findByFilters(null, null))
			.thenReturn(Collections.singletonList(testInstances.personEntity()));

		List<Person> people = service.getPeople(null, null);

		assertEquals(people, Collections.singletonList(testInstances.person()));
	}

	@Test
	@DisplayName("Should get all people with filters")
	public void shouldGetAllWithFilters() {
		when(repository.findByFilters("John", "Wayne"))
			.thenReturn(Collections.singletonList(testInstances.personEntity()));

		List<Person> people = service.getPeople("John","Wayne");

		assertEquals(people, Collections.singletonList(testInstances.person()));
	}

	@Test
	@DisplayName("Should create person")
	public void shouldCreatePerson() {
		Person person = testInstances.person();
		PersonEntity entity = testInstances.personEntity();
		when(repository.save(entity)).thenReturn(entity);

		Person saved = service.save(person);

		assertEquals(saved, person);
	}

	@Test
	@DisplayName("Should update person")
	public void shouldUpdatePerson() {
		Person person = testInstances.person();
		PersonEntity entity = testInstances.personEntity();
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(entity);

		Person updated = service.update(1L, person);

		assertEquals(updated, person);
	}

	@Test
	@DisplayName("Should not update person when not exists")
	public void shouldNotUpdatePersonWhenNotFound() {
		Person person = testInstances.person();
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(PersonNotFoundException.class, () -> service.update(1L, person));
	}

	@Test
	@DisplayName("Should delete person")
	public void shouldDeletePerson() {
		PersonEntity entity = testInstances.personEntity();
		when(repository.findById(1L))
			.thenReturn(Optional.of(testInstances.personEntity()));
		doNothing().when(repository).delete(entity);

		service.delete(1L);

		verify(repository).delete(entity);
	}

	@Test
	@DisplayName("Should not delete person when not exists")
	public void shouldNotDeleteWhenPersonNotFound() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(PersonNotFoundException.class, () -> service.delete(1L));
	}
}
