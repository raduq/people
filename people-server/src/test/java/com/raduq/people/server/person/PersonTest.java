package com.raduq.people.server.person;

import com.raduq.people.server.TestInstances;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

	@Test
	void shouldConvertToEntity() {
		Person person = new TestInstances().person();

		PersonEntity entity = person.toEntity();

		assertEquals(entity, new TestInstances().personEntity());
	}

	@Test
	void shouldConvertToEntityWhenNotFields() {
		assertEquals(Person.builder().build().toEntity(), new PersonEntity());
	}
}
