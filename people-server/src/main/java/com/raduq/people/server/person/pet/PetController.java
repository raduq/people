package com.raduq.people.server.person.pet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/people/{personId}/pets")
@Api(value = "/v1/people/{personId}/pets", tags = {"Pet Resource"})
@SwaggerDefinition(tags = {
	@Tag(name = "Pet Resource", description = "Manage people's pets")
})
public class PetController {

	@Autowired
	private PetService service;

	@ApiOperation(value = "Get pet by Id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Pet found", response = Pet.class),
		@ApiResponse(code = 404, message = "Pet with specified Id not found"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@GetMapping("/{id}")
	public Pet getPet(@ApiParam(value = "id", required = true)
					  @PathVariable(value = "id") Long id) {
		return service.getPet(id).toDTO();
	}

	@ApiOperation(value = "Get all pets")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Pet found", response = Pet.class),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@GetMapping
	public List<Pet> getPets() {
		return new PetMapper().toListOfDTO(service.getPets().spliterator());
	}

	@ApiOperation(value = "Create a new pet")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Pet created", response = Pet.class),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@PostMapping
	public Pet create(
		@ApiParam(value = "Pet to be created", required = true)
		@RequestBody Pet pet,
		@ApiParam(value = "Id of the person owner of the pet to be created", required = true)
		@PathVariable("personId") Long personId) {
		return service.save(personId, pet).toDTO();
	}

	@ApiOperation(value = "Update an existing pet")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Pet updated", response = Pet.class),
		@ApiResponse(code = 404, message = "Pet not found with specified Id"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@PutMapping("/{id}")
	public Pet update(
		@ApiParam(value = "Id of the pet", required = true)
		@PathVariable Long id,
		@ApiParam(value = "Id of the person", required = true)
		@PathVariable Long personId,
		@ApiParam(value = "Pet data to be updated", required = true)
		@RequestBody Pet pet) {
		return service.update(personId, id, pet).toDTO();
	}

	@ApiOperation(value = "Delete an existing pet")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Pet deleted"),
		@ApiResponse(code = 404, message = "Pet not found with specified Id"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long personId, @PathVariable Long id) {
		service.delete(personId, id);
	}

}
