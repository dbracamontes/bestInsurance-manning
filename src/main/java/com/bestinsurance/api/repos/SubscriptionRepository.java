package com.bestinsurance.api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bestinsurance.api.model.Subscription;
import com.bestinsurance.api.model.Subscription.SubscriptionKey;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionKey>{

}
