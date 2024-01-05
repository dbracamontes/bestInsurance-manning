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
@Table(name="states")
@Getter
@Setter
public class State {
	
	@Id
	@GeneratedValue
	@Column(name="state_id")
	private UUID stateId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="population")
	private int population;
	
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@Override
	public int hashCode() {
		return Objects.hash(stateId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return Objects.equals(stateId, other.stateId);
	}

}
