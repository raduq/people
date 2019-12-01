package com.raduq.people.server.person.address;

import com.raduq.people.server.person.PersonEntity;
import com.raduq.people.server.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	@Autowired
	private PersonService personService;

	public AddressEntity getAddress(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new AddressNotFoundException(id));
	}

	public AddressEntity update(Long personId, Long id, Address address) {
		AddressEntity foundAddress = getAddress(id);
		PersonEntity person = personService.getPerson(personId);
		AddressEntity addressToBeUpdated = address.toEntity();
		addressToBeUpdated.setId(foundAddress.getId());
		person.setAddress(address.toEntity());
		return repository.save(addressToBeUpdated);
	}

	public void delete(Long id) {
		AddressEntity addressEntity = getAddress(id);
		repository.delete(addressEntity);
	}
}
