package com.bestinsurance.api.services;

import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.PolicyView;
import com.bestinsurance.api.exceptions.NotFoundException;
import com.bestinsurance.api.model.Policy;
import com.bestinsurance.api.repos.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
/**
 * Service class managing the Policies.-.
 */
@Service
@RequiredArgsConstructor
public class PolicyService implements CrudService<Policy, PolicyView> {
	
	private final PolicyRepository policyRepository;
	private final GenericConverter genericConverter;

	@Override
	public PolicyView create(Policy policy) {
		return genericConverter.convertToType(policyRepository.save(policy), PolicyView.class );
	}

	@Override
	public List<PolicyView> findAll() {
		return policyRepository.findAll().stream()
					.map( policy -> genericConverter.convertToType(policy, PolicyView.class))
					.toList();
	}

	@Override
	public PolicyView getById(UUID id) {
		Policy policy =  policyRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Policy Not Found with id %s", id)));
		
		return genericConverter.convertToType(policy, PolicyView.class);
	}

	@Override
	public PolicyView update(UUID id, Policy policy) {
		Policy oldPolicy = policyRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Policy Not Found with id %s", id)));

		oldPolicy.setName(policy.getName());
		oldPolicy.setDescription(policy.getDescription());
		
		return  genericConverter.convertToType(policyRepository.save(oldPolicy), PolicyView.class);
	}

	@Override
	public void delete(UUID id) {
		policyRepository.deleteById(id);
	}
    
}
