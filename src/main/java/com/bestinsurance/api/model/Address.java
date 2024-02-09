package com.bestinsurance.api.model;

import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="addresses")
@Getter
@Setter
public class Address {
	
	@Id
	@GeneratedValue
	@Column(name="address_id")
	private UUID addressId;
	
	@Column(name="address")
	private String name;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@OneToOne
	@JoinColumn(name="city_id")
	private City city;
	
	@OneToOne
	@JoinColumn(name="state_id")
	private State state;
	
	/*@OneToOne(mappedBy ="address", fetch = FetchType.LAZY)
	private Customer customer;*/

	@Override
	public int hashCode() {
		return Objects.hash(addressId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(addressId, other.addressId);
	}
	
}
