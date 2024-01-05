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
@Table(name = "cities")
@Getter
@Setter
public class City {
	@Id
	@GeneratedValue
	@Column(name="city_id")
	private UUID cityId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="population")
	private int population;
	
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@OneToOne
	@JoinColumn(name="state_id")
	private State state;

	@Override
	public int hashCode() {
		return Objects.hash(cityId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(cityId, other.cityId);
	}

}
