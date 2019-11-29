package com.raduq.people.api.person.address;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
public class Address {
	private final Long id;
	private final String street;
	private final String zipCode;
	private final Long number;
	private final Long numberAddition;
	private final String city;
	private final String country;

	public AddressEntity toEntity() {
		return AddressEntity.builder()
			.id(id)
			.street(street)
			.zipCode(zipCode)
			.number(number)
			.numberAddition(numberAddition)
			.city(city)
			.country(country)
			.build();
	}
}
