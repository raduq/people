package com.raduq.people.server.person;

import com.raduq.people.server.TestInstances;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonMapperTest {

	@Test
	void shouldConvertToEntity() {
		Person person = new TestInstances().person();

		PersonEntity entity = new PersonMapper().toEntity(person);

		assertEquals(entity, new TestInstances().personEntity());
	}

	@Test
	void shouldConvertToDTO() {
		PersonEntity person = new TestInstances().personEntity();

		Person dto = new PersonMapper().toDTO(person);

		assertEquals(dto, new TestInstances().person());
	}

	@Test
	void shouldConvertToListOfDTO() {
		PersonEntity personEntity = new TestInstances().personEntity();
		List<PersonEntity> personEntities = Collections.singletonList(personEntity);

		List<Person> dtos = new PersonMapper().toListOfDTO(personEntities.spliterator());

		assertEquals(dtos.get(0), new TestInstances().person());
	}

}
