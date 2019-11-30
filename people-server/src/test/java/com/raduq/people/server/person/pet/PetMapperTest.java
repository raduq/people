package com.raduq.people.server.person.pet;

import com.raduq.people.server.TestInstances;
import com.raduq.people.server.person.address.Address;
import com.raduq.people.server.person.address.AddressEntity;
import com.raduq.people.server.person.address.AddressMapper;
import org.junit.jupiter.api.Test;

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

}
