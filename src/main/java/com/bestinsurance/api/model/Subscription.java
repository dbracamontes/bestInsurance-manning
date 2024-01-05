package com.bestinsurance.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subscriptions")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Subscription {

	@EmbeddedId
	private SubscriptionKey subscriptionKey;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "paid_price")
	private BigDecimal paidPrice;

	@CreatedDate
	@Column(name = "created")
	private OffsetDateTime created;

	@LastModifiedDate
	@Column(name = "updated")
	private OffsetDateTime updated;

	

	@Embeddable
	public class SubscriptionKey {
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "policy_id")
		private Policy policy;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "customer_id")
		private Customer customer;

		@Override
		public int hashCode() {
			return Objects.hash(policy.getPolicyId(), customer.getCustomerId());
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SubscriptionKey other = (SubscriptionKey) obj;
			return Objects.equals(policy.getPolicyId(), other.policy.getPolicyId()) && Objects.equals(customer.getCustomerId(), other.customer.getCustomerId());
		}

	}

}
