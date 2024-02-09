
package com.bestinsurance.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.model.Address;
import com.bestinsurance.api.repos.AddressRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService implements CrudService<Address> {

	private final AddressRepository addressRepository;

	@Override
	public Address create(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Optional<Address> getById(UUID id) {
		return addressRepository.findById(id);
	}

	@Override
	public Address update(UUID id, Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		addressRepository.deleteById(id);
	}

}
