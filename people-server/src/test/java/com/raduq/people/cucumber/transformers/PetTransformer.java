package com.raduq.people.cucumber.transformers;

import com.raduq.people.server.person.pet.Pet;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class PetTransformer implements TableTransformer<Pet> {

	@Override
	public Pet transform(DataTable dataTable) throws Throwable {
		Pet person = new Pet();
		List<Map<String, String>> data = dataTable.asMaps();
		data.forEach(map ->
			map.forEach((key, value) -> {
				ifContains(key, "id", value, v -> person.setId(Long.valueOf(v)));
				ifContains(key, "name", value, person::setName);
				ifContains(key, "age", value, v -> person.setAge(Integer.valueOf(v)));
			})
		);
		return person;
	}

	private void ifContains(String key, String keyCompare, String value, Consumer<String> method) {
		if (key.equals(keyCompare)) {
			method.accept(value);
		}
	}

}
