package com.raduq.people.server.person.address;

import com.raduq.people.server.TestInstances;
import com.raduq.people.server.person.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
	@Mock
	private PersonService personService;
	@InjectMocks
	private AddressService service;

	private TestInstances testInstances = new TestInstances();

	@Test
	@DisplayName("Should get address by id")
	public void shouldGetById() {
		when(repository.findById(1L))
			.thenReturn(Optional.of(testInstances.addressEntity()));

		AddressEntity address = service.getAddress(1L);

		assertEquals(address, testInstances.addressEntity());
	}

	@Test
	@DisplayName("Should update address")
	public void shouldUpdateAddress() {
		AddressEntity entity = testInstances.addressEntity();
		when(personService.getPerson(1L)).thenReturn(testInstances.personEntity());
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(entity);

		AddressEntity updated = service.update(1L, 1L, testInstances.address());

		assertEquals(updated, testInstances.addressEntity());
	}

	@Test
	@DisplayName("Should not update address when not exists")
	public void shouldNotUpdateAddressWhenNotFound() {
		Address address = testInstances.address();
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(AddressNotFoundException.class, () -> service.update(1L, 1L, address));
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
