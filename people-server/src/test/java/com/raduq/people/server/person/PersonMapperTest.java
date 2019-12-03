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

		assertEquals(entity.getId(), new TestInstances().person().getId());
		assertEquals(entity.getFirstName(), new TestInstances().person().getFirstName());
		assertEquals(entity.getLastName(), new TestInstances().person().getLastName());
		assertEquals(entity.getAddress().getId(), new TestInstances().person().getAddress().getId());
	}

	@Test
	void shouldConvertToDTO() {
		PersonEntity person = new TestInstances().personEntity();

		Person dto = new PersonMapper().toDTO(person);

		assertEquals(dto.getId(), new TestInstances().person().getId());
		assertEquals(dto.getFirstName(), new TestInstances().person().getFirstName());
		assertEquals(dto.getLastName(), new TestInstances().person().getLastName());
		assertEquals(dto.getAddress().getId(), new TestInstances().person().getAddress().getId());
	}

	@Test
	void shouldConvertToListOfDTO() {
		PersonEntity personEntity = new TestInstances().personEntity();
		List<PersonEntity> personEntities = Collections.singletonList(personEntity);

		List<Person> dtos = new PersonMapper().toListOfDTO(personEntities.spliterator());

		assertEquals(dtos.get(0).getId(), new TestInstances().person().getId());
		assertEquals(dtos.get(0).getFirstName(), new TestInstances().person().getFirstName());
		assertEquals(dtos.get(0).getLastName(), new TestInstances().person().getLastName());
		assertEquals(dtos.get(0).getAddress().getId(), new TestInstances().person().getAddress().getId());
	}

}
