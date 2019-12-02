package com.raduq.people.server.person.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
	private Long id;
	private String street;
	private String zipCode;
	private Long number;
	private Long numberAddition;
	private String city;
	private String country;

	public AddressEntity toEntity() {
		return new AddressMapper().toEntity(this);
	}
}
