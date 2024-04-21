package com.bestinsurance.api.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.StateView;
import com.bestinsurance.api.exceptions.NotFoundException;
import com.bestinsurance.api.model.State;
import com.bestinsurance.api.repos.StateRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateService implements CrudService<State, StateView> {
	private final StateRepository stateRepository;
	private final GenericConverter genericConverter;

	@Override
	public StateView create(State state) {
		return genericConverter.convertToType(stateRepository.save(state), StateView.class);
	}

	@Override
	public List<StateView> findAll() {
		return stateRepository.findAll().stream().map(state -> genericConverter.convertToType(state, StateView.class))
				.toList();
	}

	@Override
	public StateView getById(UUID id) {
		State state = stateRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("City Not Found with id %s", id)));
		return genericConverter.convertToType(state, StateView.class);
	}

	@Override
	public StateView update(UUID id, State state) {
		State oldState = stateRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("City Not Found with id %s", id)));
		
		
		oldState.setCountry(state.getCountry());
		oldState.setName(state.getName());
		oldState.setPopulation(state.getPopulation());
	
		
		return genericConverter.convertToType(stateRepository.save(oldState), StateView.class);
	}

	@Override
	public void delete(UUID id) {
		stateRepository.deleteById(id);
	}

}
