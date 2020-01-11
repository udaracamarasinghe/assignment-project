package com.uca.assignment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.uca.assignment.dbone.entities.DBOneCustomer;
import com.uca.assignment.dbone.repositories.DBOneCustomerRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private DBOneCustomerRepository dbOneCustomerRepository;

	private String commonIC1 = "17a534362135d1502e439de3c495192ce8fce6edd68f13234bc9bd38309ef4ff";
	private String commonIC2 = "2ded9cc7842563eeb3ecf4da66d91f95f13796ce40dcc16d1364ff11c226d7c8";
	private String commonCxName = "Customer One";

	@PostConstruct
	public void before() {
		DBOneCustomer dbOneCustomer1 = new DBOneCustomer();
		dbOneCustomer1.setIc(commonIC1);
		dbOneCustomer1.setCxName(commonCxName);
		dbOneCustomer1.setCxAge(34);
		dbOneCustomer1.setCxDOB(new Date("1963/10/10"));

		dbOneCustomerRepository.save(dbOneCustomer1);

		DBOneCustomer dbOneCustomer2 = new DBOneCustomer();
		dbOneCustomer2.setIc(commonIC2);
		dbOneCustomer2.setCxName(commonCxName);
		dbOneCustomer2.setCxAge(24);
		dbOneCustomer2.setCxDOB(new Date("1990/10/10"));

		dbOneCustomerRepository.save(dbOneCustomer2);
	}

	@Test
	@Order(1)
	public void testGetAll() throws Exception {
		mvc.perform(get("/db-one-customer")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{\"status\":\"OK\",\"message\":\"Existing all customers\"}", false));
	}

	@Test
	@Order(2)
	public void makeCustomer() throws Exception {
		mvc.perform(post("/db-one-customer").contentType(MediaType.APPLICATION_JSON)
				.content("{\"ic\":\"testic1\",\"cxName\": \"Sam\",\"cxAge\": 23,\"cxDOB\": \"1990-02-02\"}"))
				.andDo(print()).andExpect(status().is(201));
	}

	@Test
	@Order(3)
	public void tryMakeExistingCustomer() throws Exception {
		mvc.perform(post("/db-one-customer").contentType(MediaType.APPLICATION_JSON)
				.content("{\"ic\":\"testic1\",\"cxName\": \"Sam\",\"cxAge\": 23,\"cxDOB\": \"1990-02-02\"}"))
				.andDo(print()).andExpect(status().is(400));
	}

	@Test
	@Order(3)
	public void deleteExistingCustomer() throws Exception {
		mvc.perform(delete("/db-one-customer/17a534362135d1502e439de3c495192ce8fce6edd68f13234bc9bd38309ef4ff"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(4)
	public void makeCustomerInDBTwo() throws Exception {
		mvc.perform(post("/db-two-customer").contentType(MediaType.APPLICATION_JSON)
				.content("{\"ic\":\"testic1\",\"cxName\": \"Sam\",\"cxAge\": 23,\"cxDOB\": \"1990-02-02\"}"))
				.andDo(print()).andExpect(status().is(201));
	}

}
