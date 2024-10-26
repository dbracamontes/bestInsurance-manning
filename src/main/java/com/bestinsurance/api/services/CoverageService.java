package com.bestinsurance.api.services;


import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.CoverageView;
import com.bestinsurance.api.exceptions.NotFoundException;
import com.bestinsurance.api.model.Coverage;
import com.bestinsurance.api.repos.CoverageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * Service class managing the Coverages.-.
 */
@Service
@RequiredArgsConstructor
public class CoverageService implements CrudService<Coverage, CoverageView> {
	
	private final CoverageRepository coverageRepository;
	private final GenericConverter genericConverter;
	
	@Override
	public CoverageView create(Coverage coverage) {
    	
		return genericConverter.convertToType(coverageRepository.save(coverage), CoverageView.class);
	}
	@Override
	public List<CoverageView> findAll() {
		return coverageRepository.findAll().stream()
				.map( coverage -> genericConverter.convertToType(coverage, CoverageView.class))
				.toList();	}
	@Override
	public CoverageView getById(UUID id) {
		Coverage coverage = coverageRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Coverage Not Found with id %s", id)));

		return genericConverter.convertToType(coverage, CoverageView.class);
	}
	
	@Override
	public CoverageView update(UUID id, Coverage coverage) {
		Coverage oldCoverage = coverageRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Coverage Not Found with id %s", id)));

		oldCoverage.setName(coverage.getName());
		oldCoverage.setDescription(coverage.getDescription());
		
		return  genericConverter.convertToType(coverageRepository.save(oldCoverage), CoverageView.class);
	}
	
	@Override
	public void delete(UUID id) {
		coverageRepository.deleteById(id);	
	}	
}
