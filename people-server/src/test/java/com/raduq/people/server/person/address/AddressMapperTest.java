package com.raduq.people.server.person.address;

import com.raduq.people.server.TestInstances;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressMapperTest {

	@Test
	void shouldConvertToEntity() {
		Address address = new TestInstances().address();

		AddressEntity entity = new AddressMapper().toEntity(address);

		assertEquals(entity, new TestInstances().addressEntity());
	}

	@Test
	void shouldConvertToDTO() {
		AddressEntity address = new TestInstances().addressEntity();

		Address dto = new AddressMapper().toDTO(address);

		assertEquals(dto, new TestInstances().address());
	}

}
