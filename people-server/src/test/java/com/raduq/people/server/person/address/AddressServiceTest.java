package com.raduq.people.server.person.address;

import com.raduq.people.server.TestInstances;
import com.raduq.people.server.person.pet.Pet;
import com.raduq.people.server.person.pet.PetEntity;
import com.raduq.people.server.person.pet.PetNotFoundException;
import com.raduq.people.server.person.pet.PetRepository;
import com.raduq.people.server.person.pet.PetService;
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
public class AddressServiceTest {

	@Mock
	private AddressRepository repository;
	@InjectMocks
	private AddressService service;

	private TestInstances testInstances = new TestInstances();

	@Test
	@DisplayName("Should get address by id")
	public void shouldGetById() {
		when(repository.findById(1L))
			.thenReturn(Optional.of(testInstances.addressEntity()));

		Address address = service.getAddress(1L);

		assertEquals(address, testInstances.address());
	}

	@Test
	@DisplayName("Should get all addresses")
	public void shouldGetAll() {
		when(repository.findAll())
			.thenReturn(Collections.singletonList(testInstances.addressEntity()));

		List<Address> addresses = service.getAddresses();

		assertEquals(addresses, Collections.singletonList(testInstances.address()));
	}

	@Test
	@DisplayName("Should create address")
	public void shouldCreatePet() {
		Address address = testInstances.address();
		AddressEntity entity = testInstances.addressEntity();
		when(repository.save(entity)).thenReturn(entity);

		Address saved = service.save(address);

		assertEquals(saved, address);
	}

	@Test
	@DisplayName("Should update address")
	public void shouldUpdateAddress() {
		Address address = testInstances.address();
		AddressEntity entity = testInstances.addressEntity();
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(entity);

		Address updated = service.update(1L, address);

		assertEquals(updated, address);
	}

	@Test
	@DisplayName("Should not update address when not exists")
	public void shouldNotUpdateAddressWhenNotFound() {
		Address address = testInstances.address();
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(AddressNotFoundException.class, () -> service.update(1L, address));
	}

	@Test
	@DisplayName("Should delete address")
	public void shouldDeleteAddress() {
		AddressEntity entity = testInstances.addressEntity();
		when(repository.findById(1L))
			.thenReturn(Optional.of(testInstances.addressEntity()));
		doNothing().when(repository).delete(entity);

		service.delete(1L);

		verify(repository).delete(entity);
	}

	@Test
	@DisplayName("Should not delete address when not exists")
	public void shouldNotDeleteWhenAddressNotFound() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(AddressNotFoundException.class, () -> service.delete(1L));
	}
}
