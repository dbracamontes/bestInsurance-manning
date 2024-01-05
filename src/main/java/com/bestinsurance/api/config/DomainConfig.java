package com.bestinsurance.api.config;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.bestinsurance.api.model")
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class DomainConfig {

	@Bean // Makes ZonedDateTime compatible with auditing fields
	public DateTimeProvider auditingDateTimeProvider() {
		return () -> Optional.of(ZonedDateTime.now());
	}

}
