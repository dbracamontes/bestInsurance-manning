package com.bestinsurance.api.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.bestinsurance.api.config.DomainConfig;
import com.bestinsurance.api.model.Customer;
import com.bestinsurance.api.repos.CustomerRepository;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(DomainConfig.class)
public class CustomerJPATest {
	@ClassRule
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14.5-alpine")
			.withPassword("password").withUsername("postgres").withExposedPorts(5432, 5432)
			.withDatabaseName("best_insurance_api");
	
	@Autowired
	private CustomerRepository customerRepository;

	@BeforeAll
	public static void beforeEach() {
		postgreSQLContainer.start();
		System.out.println("exit");
	}

	@AfterAll
	public static void afterAll() {
		postgreSQLContainer.stop();
	}

	@Test
	void createCustomer() {
		Customer customer = new Customer();
		customer.setEmail("daniel@gmail.com");
		customer.setName("Daniel");
		customer.setSurname("Bracamontes");
			
		customer = customerRepository.save(customer);
		assertNotNull(customer.getCustomerId());
		assertNotNull(customer.getCreated());
	}
}
