
package com.bestinsurance.api.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.AddressView;
import com.bestinsurance.api.exceptions.NotFoundException;
import com.bestinsurance.api.model.Address;
import com.bestinsurance.api.repos.AddressRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService implements CrudService<Address, AddressView> {

	private final AddressRepository addressRepository;
	private final GenericConverter genericConverter;

	@Override
	public AddressView create(Address address) {
		return genericConverter.convertToType(addressRepository.save(address), AddressView.class);
	}

	@Override
	public List<AddressView> findAll() {
		return addressRepository.findAll().stream()
				.map(address -> genericConverter.convertToType(addressRepository.findAll(), AddressView.class))
				.toList();

	}

	@Override
	public AddressView getById(UUID id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Address Not Found with id %s", id)));

		return genericConverter.convertToType(address, AddressView.class);
	}

	@Override
	public AddressView update(UUID id, Address address) {
		Address oldAddress = addressRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Address Not Found with id %s", id)));

		oldAddress.setState(address.getState());
		oldAddress.setCity(address.getCity());
		oldAddress.setCountry(address.getCountry());
		oldAddress.setName(address.getName());
		oldAddress.setPostalCode(address.getPostalCode());
	
		return genericConverter.convertToType(oldAddress, AddressView.class);
	}

	@Override
	public void delete(UUID id) {
		addressRepository.deleteById(id);
	}

}
