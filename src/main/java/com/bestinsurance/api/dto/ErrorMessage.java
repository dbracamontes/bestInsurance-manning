package com.bestinsurance.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	public LocalDateTime timeStamp;
	public String errorMessage;
	public List<String> errors;
}
