package com.bestinsurance.api.dto;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressView {

	private UUID addressId;
	
	@NotBlank
    @Size(max=128)
	private String name;
	
	@NotBlank
	private String postalCode;
	
	@NotNull
	private CountryView country;
	
	@NotNull
	private CityView city;
	
	@NotNull
	private StateView state;
}
