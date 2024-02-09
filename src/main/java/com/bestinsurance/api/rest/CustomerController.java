package com.bestinsurance.api.rest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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

}
