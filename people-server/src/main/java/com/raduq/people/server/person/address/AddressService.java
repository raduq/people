package com.raduq.people.server.person.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;

	public Address getAddress(Long id) {
		return repository.findById(id)
			.map(AddressEntity::toDTO)
			.orElseThrow(() -> new AddressNotFoundException(id));
	}

	public List<Address> getAddresses() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
			.map(AddressEntity::toDTO)
			.collect(Collectors.toList());
	}

	public Address save(Address address) {
		return repository.save(address.toEntity()).toDTO();
	}

	public Address update(Long id, Address address) {
		return repository.save(address.toEntity()).toDTO();
	}

	public void delete(Long id) {
		AddressEntity addressEntity = repository.findById(id)
			.orElseThrow(() -> new AddressNotFoundException(id));
		repository.delete(addressEntity);
	}
}
