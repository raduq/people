package com.raduq.people.server.person.address;

import com.raduq.people.server.person.PersonEntity;
import com.raduq.people.server.person.PersonNotFoundException;
import com.raduq.people.server.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	@Autowired
	private PersonRepository personRepository;

	public Address getAddress(Long id) {
		return repository.findById(id)
			.map(AddressEntity::toDTO)
			.orElseThrow(() -> new AddressNotFoundException(id));
	}

	public Address update(Long personId, Long id, Address address) {
		AddressEntity foundAddress = repository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
		PersonEntity person = personRepository.findById(personId)
			.orElseThrow(() -> new PersonNotFoundException(personId));
		person.setAddress(address.toEntity());
		return repository.save(address.toEntity()).toDTO();
	}

	public void delete(Long id) {
		AddressEntity addressEntity = repository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
		repository.delete(addressEntity);
	}
}
