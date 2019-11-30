package com.raduq.people.server.person.pet;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/people/{id}/pets")
public class PetController {

	@Autowired
	private PetService service;

	@GetMapping("/{id}")
	public Pet getPet(Long id) {
		return service.getPet(id);
	}

	@GetMapping
	public List<Pet> getPets() {
		return service.getPets();
	}

	@PostMapping
	public Pet create(@RequestBody Pet pet) {
		return service.save(pet);
	}

	@PutMapping("/{id}")
	public Pet update(@PathVariable Long id, @RequestBody Pet pet) {
		return service.update(id, pet);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
