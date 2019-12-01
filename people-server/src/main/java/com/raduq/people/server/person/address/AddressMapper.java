package com.raduq.people.server.person.address;

public final class AddressMapper {

	public final AddressEntity toEntity(Address address) {
		return AddressEntity.builder()
			.id(address.getId())
			.street(address.getStreet())
			.zipCode(address.getZipCode())
			.number(address.getNumber())
			.numberAddition(address.getNumberAddition())
			.city(address.getCity())
			.country(address.getCountry())
			.build();
	}

	public final Address toDTO(AddressEntity address) {
		return Address.builder()
			.id(address.getId())
			.street(address.getStreet())
			.zipCode(address.getZipCode())
			.number(address.getNumber())
			.numberAddition(address.getNumberAddition())
			.city(address.getCity())
			.country(address.getCountry())
			.build();
	}

}
