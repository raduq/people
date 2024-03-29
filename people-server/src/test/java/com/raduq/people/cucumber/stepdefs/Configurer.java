package com.raduq.people.cucumber.stepdefs;

import com.raduq.people.cucumber.transformers.AddressTransformer;
import com.raduq.people.cucumber.transformers.PersonTransformer;
import com.raduq.people.cucumber.transformers.PetTransformer;
import com.raduq.people.server.person.Person;
import com.raduq.people.server.person.address.Address;
import com.raduq.people.server.person.pet.Pet;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;

public class Configurer implements TypeRegistryConfigurer {

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public void configureTypeRegistry(TypeRegistry registry) {
		registry.defineDataTableType(new DataTableType(Person.class, new PersonTransformer()));
		registry.defineDataTableType(new DataTableType(Address.class, new AddressTransformer()));
		registry.defineDataTableType(new DataTableType(Pet.class, new PetTransformer()));
	}
}
