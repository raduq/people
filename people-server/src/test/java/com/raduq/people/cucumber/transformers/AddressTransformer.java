package com.raduq.people.cucumber.transformers;

import com.raduq.people.server.person.address.Address;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AddressTransformer implements TableTransformer<Address> {

	@Override
	public Address transform(DataTable dataTable) throws Throwable {
		Address address = new Address();
		List<Map<String, String>> data = dataTable.asMaps();
		data.forEach(map ->
			map.forEach((key, value) -> {
				ifContains(key, "id", value, v -> address.setId(Long.valueOf(v)));
				ifContains(key, "street", value, address::setStreet);
				ifContains(key, "zipCode", value, address::setZipCode);
				ifContains(key, "city", value, address::setCity);
				ifContains(key, "country", value, address::setCountry);
				ifContains(key, "number", value, v -> address.setNumber(Long.valueOf(v)));
				ifContains(key, "numberAddition", value, v -> address.setNumberAddition(Long.valueOf(v)));
			})
		);
		return address;
	}

	private void ifContains(String key, String keyCompare, String value, Consumer<String> method) {
		if (key.equals(keyCompare)) {
			method.accept(value);
		}
	}

}
