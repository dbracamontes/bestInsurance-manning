package com.bestinsurance.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.model.Customer;
import com.bestinsurance.api.repos.CustomerRepository;

@Service
public class CustomerService implements CrudService<Customer> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getById(UUID id) {
		return customerRepository.findById(id);
	}

	@Override
	public Customer update(UUID id, Customer customer) {
		Optional<Customer> updateOpt = customerRepository.findById(id);
		
		if (updateOpt.isPresent()) {
			customer.setCustomerId(id);
			customer.setName(updateOpt.get().getName());
			customer.setSurname(updateOpt.get().getSurname());
			return customerRepository.save(customer);
		} else {
			logger.debug("Tried to update a not existing record");
			throw new NoSuchElementException("Entity not found in database");
		}
	}

	@Override
	public void delete(UUID id) {
		customerRepository.deleteById(id);
	}

}
