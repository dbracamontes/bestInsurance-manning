package com.bestinsurance.api.rest;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;
import java.util.UUID;
import com.bestinsurance.api.dto.CustomerCreation;
import com.bestinsurance.api.dto.CustomerView;
import com.bestinsurance.api.services.CustomerService;
import com.bestinsurance.api.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
class CustomerControllerTest {

	// https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
	@Autowired
	private MockMvc mockMvc;

	private static ObjectMapper objectMapper;

	@MockBean
	private CustomerService customerService;

	@BeforeAll
	static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	void createCustomer(@Value("classpath:data/customer/customerCreate.json") Resource customerCreateResource,
			@Value("classpath:data/customer/customerView.json") Resource customerViewResource) throws Exception {
		String customerCreateJson = FileUtils.asString(customerCreateResource);
		String customerViewJson = FileUtils.asString(customerViewResource);

		CustomerView customerView = objectMapper.readValue(customerViewJson, CustomerView.class);
		when(customerService.create(any(CustomerCreation.class))).thenReturn(customerView);

		mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(customerCreateJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.customerId").exists()).andReturn();
	}

	@Test
	void deleteCustomer() throws Exception {
		doNothing().when(customerService).delete(any(UUID.class));
		mockMvc.perform(delete("/customer/e58ed763-928c-4155-bee9-fdbaaadc15f3")).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void getAllCustomers(@Value("classpath:data/customer/customersView.json") Resource customersView) throws Exception {
		String customersViewJson = FileUtils.asString(customersView);
		List<CustomerView> customers = objectMapper.readValue(customersViewJson,
				new TypeReference<List<CustomerView>>() {
				});
		when(customerService.findAll()).thenReturn(customers);

		mockMvc.perform(get("/customer")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0].name", is("Daniel"))).andExpect(jsonPath("$[1].name", is("Test")))
				.andExpect(jsonPath("$", hasSize(3))).andReturn();
	}

	@Test
	void getById(@Value("classpath:data/customer/customerView.json") Resource customersView) throws Exception {
		String customerViewJson = FileUtils.asString(customersView);
		CustomerView customer = objectMapper.readValue(customerViewJson, CustomerView.class);
		when(customerService.getById(any(UUID.class))).thenReturn(customer);

		mockMvc.perform(get("/customer/669f5915-d6a2-474a-a508-f658220e6f84"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.name", is("Daniel")))
				.andExpect(jsonPath("$.customerId", is("669f5915-d6a2-474a-a508-f658220e6f84")))
				.andReturn();
	}
	
	@Test
	void updateCustomer(@Value("classpath:data/customer/customerCreate.json") Resource customerCreate, @Value("classpath:data/customer/customerView.json") Resource customersView) throws Exception {
		String customerViewJson = FileUtils.asString(customersView);
		String customerCreateJson = FileUtils.asString(customerCreate);
		CustomerView customerView = objectMapper.readValue(customerViewJson, CustomerView.class);
		when(customerService.create(any(CustomerCreation.class))).thenReturn(customerView);

		System.out.println(customerView.getCustomerId());
		
		MvcResult mvcResult =  mockMvc.perform(put("/customer/669f5915-d6a2-474a-a508-f658220e6f84").contentType(MediaType.APPLICATION_JSON).content(customerCreateJson))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.name", is(customerView.getName())))
				.andExpect(jsonPath("$.customerId", is(customerView.getCustomerId().toString())))
				.andReturn();
		
		String responseData = mvcResult.getResponse().getContentAsString();
		System.out.println(responseData);
	}

	public String convertToJSon(CustomerController customerController) {
		try {
			return new ObjectMapper().writeValueAsString(customerController);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}
}
