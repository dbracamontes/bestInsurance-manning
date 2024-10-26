package com.bestinsurance.api.rest;

import com.bestinsurance.api.dto.PolicyView;
import com.bestinsurance.api.model.Policy;
import com.bestinsurance.api.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;
/**
 * Controller impelmenting the Policies crud API
 */
@RestController
@RequestMapping("/policies")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @PostMapping
	public PolicyView createPolicy(@Validated @RequestBody Policy policy) {
		return policyService.create(policy);
	}

	@DeleteMapping("/{id}")
	public void deletePolicy(@PathVariable UUID id) {
		policyService.delete(id);
	}

	@GetMapping
	public List<PolicyView> getAllPolicies() {
		return policyService.findAll();
	}

	@GetMapping("/{id}")
	public PolicyView getPolicyById(@PathVariable UUID id) {
		return policyService.getById(id);
	}

	@PutMapping("/{id}")
	public PolicyView updatePolicyById(@Validated @RequestBody Policy policy) {
		return policyService.create(policy);
	}
}
