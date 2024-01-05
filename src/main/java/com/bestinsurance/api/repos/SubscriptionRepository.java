package com.bestinsurance.api.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bestinsurance.api.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID>{

}
