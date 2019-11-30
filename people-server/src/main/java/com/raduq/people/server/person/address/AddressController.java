package com.raduq.people.server.person.address;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/people/{id}/addresses")
public class AddressController {

	@Autowired
	private AddressService service;

	@GetMapping("/{id}")
	public Address getAddress(Long id) {
		return service.getAddress(id);
	}

	@GetMapping
	public List<Address> getAddresses() {
		return service.getAddresses();
	}

	@PostMapping
	public Address create(@RequestBody Address address) {
		return service.save(address);
	}

	@PutMapping("/{id}")
	public Address update(@PathVariable Long id, @RequestBody Address address) {
		return service.update(id, address);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
