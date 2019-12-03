package com.raduq.people.server.person;

import com.raduq.people.server.person.address.AddressEntity;
import com.raduq.people.server.person.pet.PetEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "person")
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "The person first name should not be null")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank(message = "The person last name should not be null")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "birth_date")
	private Date birthDate;

	@OneToOne(cascade = CascadeType.ALL)
	private AddressEntity address;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PetEntity> pets;

	public Person toDTO() {
		return new PersonMapper().toDTO(this);
	}

	public PersonEntity addPet(PetEntity pet) {
		this.pets.add(pet);
		return this;
	}

	public PersonEntity putPet(PetEntity oldPet, PetEntity pet) {
		this.pets.remove(oldPet);
		this.pets.add(pet);
		return this;
	}

}
