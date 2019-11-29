package com.raduq.people.api.person.address;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/people/{id}/addresses")
public class AddressController {

	@GetMapping
	public AddressEntity getAddress() {
		return new AddressEntity(1L, "Alexanderplein", "1010AA", 1L, 1L, "Amsterdam", "NL");
	}

}
