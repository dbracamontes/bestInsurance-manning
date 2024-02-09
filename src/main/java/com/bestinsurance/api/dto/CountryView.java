package com.bestinsurance.api.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CountryView {
	private UUID countryId;
	
	@NotBlank
	private String name;
}
