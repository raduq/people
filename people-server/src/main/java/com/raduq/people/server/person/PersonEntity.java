package com.raduq.people.server.person;

import com.raduq.people.server.person.address.AddressEntity;
import com.raduq.people.server.person.pet.PetEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "person")
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "birth_date")
	private LocalDateTime birthDate;
	@ManyToOne
	private AddressEntity address;
	@OneToMany(fetch = FetchType.LAZY)
	private List<PetEntity> pets;

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