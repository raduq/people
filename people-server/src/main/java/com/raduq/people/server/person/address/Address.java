package com.raduq.people.server.person.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
