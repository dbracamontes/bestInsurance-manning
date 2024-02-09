package com.bestinsurance.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.model.Country;
import com.bestinsurance.api.repos.CountryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService implements CrudService<Country> {

	private final CountryRepository countryRepository;

	@Override
	public Country create(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public Optional<Country> getById(UUID id) {
		return countryRepository.findById(id);
	}

	@Override
	public Country update(UUID id, Country country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		countryRepository.deleteById(id);
	}

}
