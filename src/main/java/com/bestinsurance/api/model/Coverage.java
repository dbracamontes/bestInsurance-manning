package com.bestinsurance.api.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="coverages")
@Getter
@Setter
public class Coverage {
	
	@Id
	@GeneratedValue
	@Column(name="coverage_id")
	private UUID coverageId;
	
	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;

	@ManyToMany(mappedBy = "coverages")
	private List<Policy> policy;

	@Override
	public int hashCode() {
		return Objects.hash(coverageId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coverage other = (Coverage) obj;
		return Objects.equals(coverageId, other.coverageId);
	}
}
