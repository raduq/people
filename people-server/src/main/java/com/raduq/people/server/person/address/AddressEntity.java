package com.raduq.people.server.person.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "address")
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "street", nullable = false)
	private String street;
	@Column(name = "zip_code", nullable = false)
	private String zipCode;
	@Column(name = "number")
	private Long number;
	@Column(name = "number_addition")
	private Long numberAddition;
	@Column(name = "city")
	private String city;
	@Column(name = "country")
	private String country;

	public Address toDTO() {
		return new AddressMapper().toDTO(this);
	}

}
