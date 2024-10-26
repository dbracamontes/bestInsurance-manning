package com.bestinsurance.api.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.SubscriptionCreation;
import com.bestinsurance.api.dto.SubscriptionView;
import com.bestinsurance.api.exceptions.NotFoundException;
import com.bestinsurance.api.model.Subscription;
import com.bestinsurance.api.model.Subscription.SubscriptionKey;
import com.bestinsurance.api.repos.SubscriptionRepository;
import com.bestinsurance.api.model.Policy;
import com.bestinsurance.api.model.Customer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
	
	private SubscriptionRepository subscriptionRepository;
	private final GenericConverter genericConverter;
    private PolicyService policyService;
    private CustomerService customerService;

	public SubscriptionView create(Subscription subscription) {
		return genericConverter.convertToType(subscriptionRepository.save(subscription), SubscriptionView.class);
	}
	
	public SubscriptionView create(SubscriptionCreation subscriptionCreation) {
		Policy policy =  genericConverter.convertToType(policyService.getById(subscriptionCreation.getPolicyId()), Policy.class);
    	Customer customer = genericConverter.convertToType(customerService.getById(subscriptionCreation.getCustomerId()), Customer.class);
		Subscription subscription = genericConverter.convertToType(subscriptionCreation, Subscription.class);
		subscription.setSubscriptionKey(subscription.new SubscriptionKey(policy, customer));
		
		return create(subscription);
	}

	public List<SubscriptionView> findAll() {
		return subscriptionRepository.findAll().stream().map(subscription -> genericConverter.convertToType(subscription, SubscriptionView.class)).toList();
	}

	public SubscriptionView getById(UUID customerId, UUID policyId) {
		Subscription subscription =  subscriptionRepository.findById(generateSubscriptionKey(customerId, policyId)).orElseThrow(() -> new NotFoundException(String.format("Subscription Not Found with id %s_%s", customerId, policyId)));
		
		return genericConverter.convertToType(subscription, SubscriptionView.class); 
	}

	public SubscriptionView update(UUID customerId, UUID policyId, SubscriptionCreation subscription) {
		Subscription oldSubscrition =  subscriptionRepository.findById(generateSubscriptionKey(customerId, policyId)).orElseThrow(() -> new NotFoundException(String.format("Subscription Not Found with id %s_%s", customerId, policyId)));
		oldSubscrition.setPaidPrice(subscription.getPaidPrice());
		oldSubscrition.setSubscriptionKey(generateSubscriptionKey(customerId, policyId));
		
		return genericConverter.convertToType(subscriptionRepository.save(oldSubscrition), SubscriptionView.class);
	}


	public void delete(UUID customerId, UUID policyId) {
		subscriptionRepository.deleteById(generateSubscriptionKey(customerId,policyId));
	}

	private SubscriptionKey generateSubscriptionKey(UUID customerId, UUID policyId) {
		Subscription subscription = new Subscription();
		Policy policy =  genericConverter.convertToType(policyService.getById(policyId), Policy.class);
    	Customer customer = genericConverter.convertToType(customerService.getById(customerId), Customer.class);
    	
    	return subscription.new SubscriptionKey(policy, customer);
	}
}
