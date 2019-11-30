package com.raduq.people.server.person;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/people")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping(value = "/{id}")
	public Person getPerson(@PathVariable Long id) {
		return service.getPerson(id);
	}

	@GetMapping
	public List<Person> getAll() {
		return service.getPeople();
	}

	@PostMapping
	public Person create(@RequestBody Person person) {
		return service.save(person);
	}

	@PutMapping("/{id}")
	public Person update(@PathVariable Long id, @RequestBody Person person) {
		return service.update(id, person);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
