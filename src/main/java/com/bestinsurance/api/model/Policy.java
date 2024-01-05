package com.bestinsurance.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="policies")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Policy {
	
	@Id
	@GeneratedValue
	@Column(name="policy_id")
	private UUID policyId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	

	@Column(name="price")
	private BigDecimal price;
	
	@CreatedDate
	@Column(name="created")
	private OffsetDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated")
	private OffsetDateTime updatedAt;
	
	@OneToMany(mappedBy = "subscriptionKey.policy")
	private List<Subscription> subscriptions;

	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "policies_coverages",
            joinColumns = {@JoinColumn(name = "policy_id")},
            inverseJoinColumns = {@JoinColumn(name = "coverage_id")}
    )
    private List<Coverage> coverages;

	@Override
	public int hashCode() {
		return Objects.hash(policyId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Policy other = (Policy) obj;
		return Objects.equals(policyId, other.policyId);
	}

}
