package com.raduq.people.cucumber.transformers;

import com.raduq.people.server.TestInstances;
import com.raduq.people.server.person.Person;
import com.raduq.people.server.person.pet.Pet;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class PersonTransformer implements TableTransformer<Person> {

	@Override
	public Person transform(DataTable dataTable) throws Throwable {
		Person person = new Person();
		person.setPets(new ArrayList<>());
		List<Map<String, String>> data = dataTable.asMaps();
		data.forEach(map ->
			map.forEach((key, value) -> {
				ifContains(key, "id", value, v -> person.setId(Long.valueOf(v)));
				ifContains(key, "firstName", value, person::setFirstName);
				ifContains(key, "lastName", value, person::setLastName);
				ifContains(key, "birthDate", value, v -> person.setBirthDate(toDate(v)));
				ifContains(key, "pet", value, v -> person.getPets().addAll(addPets(v)));
				ifContains(key, "address", value, v -> person.setAddress(new TestInstances().address()));
			})
		);
		return person;
	}

	private List<Pet> addPets(String param) {
		Long id = Long.parseLong(param.substring(0, param.indexOf("-")));
		List<Pet> pets = new ArrayList<>();
		pets.add(new Pet(id, param.substring(param.indexOf("-")), 1));
		return pets;
	}

	private void ifContains(String key, String keyCompare, String value, Consumer<String> method) {
		if (key.equals(keyCompare)) {
			method.accept(value);
		}
	}

	private Date toDate(String value) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

}
