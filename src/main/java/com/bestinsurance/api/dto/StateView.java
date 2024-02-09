package com.bestinsurance.api.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StateView {
	private UUID stateId;
	
	@NotBlank
	private String name;
}
