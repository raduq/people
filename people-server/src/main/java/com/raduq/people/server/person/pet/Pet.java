package com.raduq.people.server.person.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
	private Long id;
	private String name;
	private Integer age;

	public PetEntity toEntity() {
		return new PetMapper().toEntity(this);
	}
}
