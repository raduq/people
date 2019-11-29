package com.raduq.people.api.person;

import com.raduq.people.api.person.address.AddressEntity;
import com.raduq.people.api.person.pet.PetEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity(name = "person")
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	private final String firstName;
	private final String lastName;
	private final LocalDateTime birthDate;
	@ManyToOne
	private final AddressEntity address;
	@OneToMany(fetch = FetchType.LAZY)
	private final List<PetEntity> pets;

	public Person toDTO() {
		return Person.builder()
			.id(id)
			.firstName(firstName)
			.lastName(lastName)
			.birthDate(birthDate)
			.address(address.toDTO())
			.build();
	}

}
