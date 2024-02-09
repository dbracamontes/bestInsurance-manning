package com.bestinsurance.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.bestinsurance.api.model.State;
import com.bestinsurance.api.repos.StateRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateService implements CrudService<State> {
	private final StateRepository stateRepository;
	
	@Override
	public State create(State state) {
		return stateRepository.save(state);
	}

	@Override
	public List<State> findAll() {
		return stateRepository.findAll();
	}

	@Override
	public Optional<State> getById(UUID id) {
		return stateRepository.findById(id);
	}

	@Override
	public State update(UUID id, State obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		stateRepository.deleteById(id);
	}

}
