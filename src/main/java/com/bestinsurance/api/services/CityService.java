package com.bestinsurance.api.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.CityView;
import com.bestinsurance.api.exceptions.NotFoundException;
import com.bestinsurance.api.model.City;
import com.bestinsurance.api.repos.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService implements CrudService<City, CityView> {

	private final CityRepository cityRepository;
	private final GenericConverter genericConverter;

	@Override
	public CityView create(City city) {
		return genericConverter.convertToType(city, CityView.class);
	}

	@Override
	public List<CityView> findAll() {
		return cityRepository.findAll().stream().map(city -> genericConverter.convertToType(city, CityView.class))
				.toList();
	}

	@Override
	public CityView getById(UUID id) {
		City city = cityRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("City Not Found with id %s", id)));

		return genericConverter.convertToType(city, CityView.class);
	}

	@Override
	public CityView update(UUID id, City city) {
		City oldCity = cityRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("City Not Found with id %s", id)));

		oldCity.setCountry(city.getCountry());
		oldCity.setName(oldCity.getName());
		oldCity.setPopulation(city.getPopulation());
		oldCity.setState(city.getState());
		return genericConverter.convertToType(oldCity, CityView.class);
	}

	@Override
	public void delete(UUID id) {
		cityRepository.deleteById(id);
	}

}
