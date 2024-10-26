package com.bestinsurance.api.rest;

import com.bestinsurance.api.dto.CoverageView;
import com.bestinsurance.api.model.Coverage;
import com.bestinsurance.api.services.CoverageService;
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
 * Controller impelmenting the Coverages crud API
 */
@RestController
@RequestMapping("/coverages")
public class CoverageController  {
    
	@Autowired
    private CoverageService coverageService;
    
    @PostMapping
	public CoverageView createCustomer(@Validated @RequestBody Coverage coverageCreation) {
		return coverageService.create(coverageCreation);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable UUID id) {
		coverageService.delete(id);
	}

	@GetMapping
	public List<CoverageView> getAllCoverages() {
		return coverageService.findAll();
	}

	@GetMapping("/{id}")
	public CoverageView getById(@PathVariable UUID id) {
		return coverageService.getById(id);
	}

	@PutMapping("/{id}")
	public CoverageView updateById(@Validated @RequestBody Coverage customer) {
		return coverageService.create(customer);
	}
}
