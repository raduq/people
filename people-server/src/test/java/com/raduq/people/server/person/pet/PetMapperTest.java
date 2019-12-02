package com.raduq.people.server.person.pet;

import com.raduq.people.server.TestInstances;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetMapperTest {

	@Test
	void shouldConvertToEntity() {
		Pet pet = new TestInstances().pet();

		PetEntity entity = new PetMapper().toEntity(pet);

		assertEquals(entity, new TestInstances().petEntity());
	}

	@Test
	void shouldConvertToDTO() {
		PetEntity pet = new TestInstances().petEntity();

		Pet dto = new PetMapper().toDTO(pet);

		assertEquals(dto, new TestInstances().pet());
	}

	@Test
	void shouldConvertToListOfDTO() {
		PetEntity pet = new TestInstances().petEntity();
		List<PetEntity> petEntities = Collections.singletonList(pet);

		List<Pet> dtos = new PetMapper().toListOfDTO(petEntities.spliterator());

		assertEquals(dtos.get(0), new TestInstances().pet());
	}
}
