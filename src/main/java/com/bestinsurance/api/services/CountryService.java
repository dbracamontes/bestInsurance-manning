package com.bestinsurance.api.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.CountryView;
import com.bestinsurance.api.exceptions.NotFoundException;
import com.bestinsurance.api.model.Country;
import com.bestinsurance.api.repos.CountryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService implements CrudService<Country, CountryView> {

	private final CountryRepository countryRepository;
	private final GenericConverter genericConverter;

	@Override
	public CountryView create(Country country) {
		return genericConverter.convertToType(countryRepository.save(country), CountryView.class);
	}

	@Override
	public List<CountryView> findAll() {
		return countryRepository.findAll().stream()
				.map(country -> genericConverter.convertToType(country, CountryView.class))
				.collect(Collectors.toList());
	}

	@Override
	public CountryView getById(UUID id) {
		Country country = countryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Country Not Found with id %s", id)));

		return genericConverter.convertToType(country, CountryView.class);
	}

	@Override
	public CountryView update(UUID id, Country country) {
		Country oldCountry = countryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Country Not Found with id %s", id)));

		oldCountry.setName(country.getName());
		oldCountry.setPopulation(country.getPopulation());
		return null;
	}

	@Override
	public void delete(UUID id) {
		countryRepository.deleteById(id);
	}

}
