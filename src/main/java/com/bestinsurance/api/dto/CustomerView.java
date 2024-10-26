package com.bestinsurance.api.dto;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerView {

	private UUID customerId;

	@NotBlank
	@Size(max = 64)
	private String name;

	@NotBlank
	@Size(max = 64)
	private String surname;

	@NotBlank
	@Email
	private String email;

	private LocalDate birthDate;

	@NotNull
	private AddressView address;
}
