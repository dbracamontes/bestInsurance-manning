package com.bestinsurance.api.rest;

import com.bestinsurance.api.dto.SubscriptionCreation;
import com.bestinsurance.api.dto.SubscriptionView;
import com.bestinsurance.api.services.SubscriptionService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * Controller implementing the Subscription crud API
 * This class extends the AbstractCrudController because it has a composite id
 */
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionView createSubscription(@Validated @RequestBody SubscriptionCreation subscription) {
    	return subscriptionService.create(subscription);
    }
    
    @DeleteMapping("/{customerId}/{policyId}")
	public void deleteCustomer(@PathVariable UUID customerId, @PathVariable UUID policyId) {
    	subscriptionService.delete(customerId, policyId);
	}

	@GetMapping
	public List<SubscriptionView> getAllCoverages() {
		return subscriptionService.findAll();
	}

	@PutMapping("/{customerId}/{policyId}")
	public SubscriptionView updateById(@PathVariable UUID customerId, @PathVariable UUID policyId, @Validated @RequestBody SubscriptionCreation subscription) {
		return subscriptionService.update(customerId,policyId, subscription);
	}
	
    @GetMapping("/{customerId}/{policyId}")
    public SubscriptionView getById(@PathVariable UUID customerId, @PathVariable UUID policyId) {
       return subscriptionService.getById(customerId, policyId);
    }
}
