package com.raduq.people.server.person.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pet")
public class PetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "age")
	private Integer age;

	public Pet toDTO() {
		return Pet.builder()
			.id(id)
			.name(name)
			.age(age)
			.build();
	}

}
