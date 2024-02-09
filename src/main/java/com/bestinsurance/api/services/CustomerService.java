package com.bestinsurance.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.dto.CustomerCreation;
import com.bestinsurance.api.dto.CustomerView;
import com.bestinsurance.api.exceptions.NotFoundException;
import com.bestinsurance.api.model.Address;
import com.bestinsurance.api.model.City;
import com.bestinsurance.api.model.Country;
import com.bestinsurance.api.model.Customer;
import com.bestinsurance.api.model.State;
import com.bestinsurance.api.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements CrudService<Customer> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final CustomerRepository customerRepository;
	private final CityService cityService;
	private final StateService stateService;
	private final CountryService countryService;
	private final ModelMapper modelMapper;
	
	@Override
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public CustomerView create(CustomerCreation customerDto) {
		Optional<City> city = cityService.getById(customerDto.getAddress().getCityId());
		Optional<State> state =  stateService.getById(customerDto.getAddress().getStateId());
		Optional<Country> country =  countryService.getById(customerDto.getAddress().getCountryId());
		
		Customer customer = modelMapper.map(customerDto, Customer.class);
		Address address = customer.getAddress();
		
		if(city.isPresent())
			address.setCity(city.get());
		else
			throw new NotFoundException(String.format("City Not Found with id %s", customerDto.getAddress().getCityId()));
		
		if(state.isPresent())
			address.setState(state.get());
		else
			throw new NotFoundException(String.format("State Not Found with id %s", customerDto.getAddress().getStateId()));
		
		if(country.isPresent())
			 address.setCountry(country.get());
		else
			throw new NotFoundException(String.format("Country Not Found with id %s", customerDto.getAddress().getCountryId()));
		
		return modelMapper.map(create(customer), CustomerView.class);	
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
