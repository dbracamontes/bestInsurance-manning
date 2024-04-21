package com.bestinsurance.api.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bestinsurance.api.dto.CustomerCreation;
import com.bestinsurance.api.dto.CustomerView;
import com.bestinsurance.api.services.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping
	public CustomerView createCustomer(@Validated @RequestBody CustomerCreation customer) {
		return customerService.create(customer);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable UUID id) {
		customerService.delete(id);
	}

	@GetMapping
	public List<CustomerView> getAllCustomers() {
		return customerService.findAll();
	}

	@GetMapping("/{id}")
	public CustomerView getById(@PathVariable UUID id) {
		return customerService.getById(id);
	}

	@PutMapping("/{id}")
	public CustomerView updateById(@Validated @RequestBody CustomerCreation customer) {
		return customerService.create(customer);
	}

}
