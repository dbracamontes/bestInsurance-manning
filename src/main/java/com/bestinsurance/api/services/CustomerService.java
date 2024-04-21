
package com.bestinsurance.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.CityView;
import com.bestinsurance.api.dto.CountryView;
import com.bestinsurance.api.dto.CustomerCreation;
import com.bestinsurance.api.dto.CustomerView;
import com.bestinsurance.api.dto.StateView;
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
public class CustomerService implements CrudService<Customer, CustomerView> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final CustomerRepository customerRepository;
	private final CityService cityService;
	private final StateService stateService;
	private final CountryService countryService;
	private final GenericConverter genericConverter;

	@Override
	public CustomerView create(Customer customer) {
		return genericConverter.convertToType(customerRepository.save(customer), CustomerView.class);
	}

	public CustomerView create(CustomerCreation customerDto) {
		CityView city = cityService.getById(customerDto.getAddress().getCityId());
		StateView state = stateService.getById(customerDto.getAddress().getStateId());
		CountryView country = countryService.getById(customerDto.getAddress().getCountryId());

		Customer customer = genericConverter.convertToType(customerDto, Customer.class);
		Address address = customer.getAddress();

		address.setCity(genericConverter.convertToType(city, City.class));
		address.setState(genericConverter.convertToType(state, State.class));
		address.setCountry(genericConverter.convertToType(country, Country.class));

		return genericConverter.convertToType(create(customer), CustomerView.class);
	}

	@Override
	public List<CustomerView> findAll() {
		List<Customer> customers = customerRepository.findAll();

		return customers.stream().map(customer -> genericConverter.convertToType(customer, CustomerView.class))
				.toList();

	}

	@Override
	public CustomerView getById(UUID id) {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if (optCustomer.isPresent()) {
			Customer customer = optCustomer.get();
			return genericConverter.convertToType(customer, CustomerView.class);
		} else {
			throw new NotFoundException(String.format("Customer Not Found with id %s", id));
		}
	}

	@Override
	public CustomerView update(UUID id, Customer customer) {
		Optional<Customer> updateOpt = customerRepository.findById(id);

		if (updateOpt.isPresent()) {
			customer.setCustomerId(id);
			customer.setName(updateOpt.get().getName());
			customer.setSurname(updateOpt.get().getSurname());
			return genericConverter.convertToType(customerRepository.save(customer), CustomerView.class);
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
