package com.raduq.people.server.person;

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
@RequestMapping(value = "/v1/people")
@Api(value = "/v1/people", tags = {"People Resource"})
@SwaggerDefinition(tags = {
	@Tag(name = "People Resource", description = "Manage application's people")
})
public class PersonController {

	@Autowired
	private PersonService service;

	@ApiOperation(value = "Get person by Id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Person found", response = Person.class),
		@ApiResponse(code = 404, message = "Person with specified Id not found"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@GetMapping(value = "/{id}")
	public Person getPerson(
		@ApiParam(value = "id", required = true)
		@PathVariable Long id) {
		return service.getPerson(id).toDTO();
	}

	@ApiOperation(value = "Get person by filters")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "People found", response = Person.class),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@GetMapping
	public List<Person> getAll(
		@ApiParam(value = "First name of the person")
		@RequestParam(value = "first-name", required = false) String firstName,
		@ApiParam(value = "Last name of the person")
		@RequestParam(value = "last-name", required = false) String lastName) {
		return new PersonMapper()
			.toListOfDTO(service.getPeople(firstName, lastName).spliterator());
	}

	@ApiOperation(value = "Create a new person")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Person created", response = Person.class),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@PostMapping
	public Person create(
		@ApiParam(value = "Person to be created", required = true)
		@RequestBody Person person) {
		return service.save(person).toDTO();
	}

	@ApiOperation(value = "Update an existing person")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Person updated", response = Person.class),
		@ApiResponse(code = 404, message = "Person not found with specified Id"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@PutMapping("/{id}")
	public Person update(
		@ApiParam(value = "Id of the person", required = true)
		@PathVariable Long id,
		@ApiParam(value = "Person data to be updated", required = true)
		@RequestBody Person person) {
		return service.update(id, person).toDTO();
	}

	@ApiOperation(value = "Delete an existing person")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Person deleted"),
		@ApiResponse(code = 404, message = "Person not found with specified Id"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
