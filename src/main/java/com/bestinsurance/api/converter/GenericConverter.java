package com.bestinsurance.api.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenericConverter {

	private final ModelMapper modelMapper;
	
	/**
	 * 
	 * @param <U>
	 * @param source
	 * @param targetType
	 * @return
	 */
	public <U> U convertToType(Object source, Class<U> resultClass) {
        return modelMapper.map(source, resultClass);
    }
}
