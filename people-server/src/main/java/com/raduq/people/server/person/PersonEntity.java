package com.raduq.people.server.person;

import com.raduq.people.server.person.address.AddressEntity;
import com.raduq.people.server.person.pet.PetEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "birth_date")
	private Date birthDate;
	@ManyToOne(cascade = CascadeType.ALL)
	private AddressEntity address;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PetEntity> pets;

	public Person toDTO() {
		return new PersonMapper().toDTO(this);
	}

	public PersonEntity addPet(PetEntity pet){
		this.pets.add(pet);
		return this;
	}

	public PersonEntity putPet(int index, PetEntity pet){
		this.pets.set(index, pet);
		return this;
	}

}
