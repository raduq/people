package com.raduq.people.server.person.address;

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
@RequestMapping("/v1/people/{personId}/addresses")
@Api(value = "/v1/people/{personId}/addresses", tags = {"Address Resource"})
@SwaggerDefinition(tags = {
	@Tag(name = "Address Resource", description = "Manage people's addresses")
})
public class AddressController {

	@Autowired
	private AddressService service;

	@ApiOperation(value = "Get address by Id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Address found", response = Address.class),
		@ApiResponse(code = 404, message = "Address with specified Id not found"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@GetMapping("/{id}")
	public Address getAddress(
		@ApiParam(value = "id", required = true)
		@PathVariable("id") Long id) {
		return service.getAddress(id);
	}

	@ApiOperation(value = "Update an existing address")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Address updated", response = Address.class),
		@ApiResponse(code = 404, message = "Address not found with specified Id"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@PutMapping("/{id}")
	public Address update(@PathVariable Long personId, @PathVariable Long id, @RequestBody Address address) {
		return service.update(personId, id, address);
	}

	@ApiOperation(value = "Delete an existing address")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Address deleted"),
		@ApiResponse(code = 404, message = "Address not found with specified Id"),
		@ApiResponse(code = 500, message = "Server failed to respond")
	})
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
