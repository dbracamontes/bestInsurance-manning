package com.bestinsurance.api.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class CityView {	
	private UUID cityId;
	private String name;
}
