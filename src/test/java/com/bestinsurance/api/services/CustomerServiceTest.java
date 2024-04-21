package com.bestinsurance.api.services;

import com.bestinsurance.api.config.DomainConfig;
import com.bestinsurance.api.converter.GenericConverter;
import com.bestinsurance.api.dto.CustomerView;
import com.bestinsurance.api.model.Customer;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(DomainConfig.class)
@Transactional
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private GenericConverter genericConverter;

	@ClassRule
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14.5-alpine")
			.withPassword("password").withUsername("postgres").withExposedPorts(5432, 5432)
			.withDatabaseName("best_insurance_api");

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

		CustomerView customerView = customerService.create(customer);
		assertNotNull(customerView.getCustomerId());
	}

	@Test
	void getAllCustomers() {
		List<CustomerView> customers = customerService.findAll();
		assertEquals(0, customers.size());

		Customer customer = new Customer();
		customer.setEmail("daniel@gmail.com");
		customer.setName("Daniel");
		customer.setSurname("Bracamontes");

		customerService.create(customer);
		customers = customerService.findAll();

		assertEquals(1, customers.size());
	}

	@Test
	void getCustomerById() {
		Customer customer = new Customer();
		customer.setEmail("daniel@gmail.com");
		customer.setName("Daniel");
		customer.setSurname("Bracamontes");

		CustomerView customerView = customerService.create(customer);

		CustomerView customerById = customerService.getById(customer.getCustomerId());
		assertNotNull(customerById.getCustomerId());
		assertEquals(customerView.getCustomerId(), customerById.getCustomerId());
		assertEquals(customerView.getEmail(), customerById.getEmail());
		assertEquals(customerView.getName(), customerById.getName());
	}

	@Test
	void deleteCustomer() {
		Customer customer = new Customer();
		customer.setEmail("daniel@gmail.com");
		customer.setName("Daniel");
		customer.setSurname("Bracamontes");

		CustomerView customerView = customerService.create(customer);

		customerService.delete(customerView.getCustomerId());
		List<CustomerView> customers = customerService.findAll();
		assertEquals(0, customers.size());
	}

	@Test
	void updateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("daniel@gmail.com");
		customer.setName("Daniel");
		customer.setSurname("Bracamontes");

		CustomerView customerView = customerService.create(customer);
		customerView.setEmail("test@email.com");

		customerView = customerService.update(customer.getCustomerId(),
				genericConverter.convertToType(customerView, Customer.class));

		assertEquals("test@email.com", customerView.getEmail());
	}

}
