package com.bestinsurance.api.dto;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressCreation {

	private UUID addressId;
	
	@NotBlank
    @Size(max=128)
	private String name;
	
	@NotBlank
	private String postalCode;
	
	@NotNull
	private UUID countryId;
	
	@NotNull
	private UUID cityId;
	
	@NotNull
	private UUID stateId;
}
