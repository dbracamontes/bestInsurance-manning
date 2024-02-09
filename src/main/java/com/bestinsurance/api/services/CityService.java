package com.bestinsurance.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.model.City;
import com.bestinsurance.api.repos.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService implements CrudService<City> {

	private final CityRepository cityRepository;

	@Override
	public City create(City city) {
		return cityRepository.save(city);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	@Override
	public Optional<City> getById(UUID id) {
		return cityRepository.findById(id);
	}

	@Override
	public City update(UUID id, City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		cityRepository.deleteById(id);

	}

}
