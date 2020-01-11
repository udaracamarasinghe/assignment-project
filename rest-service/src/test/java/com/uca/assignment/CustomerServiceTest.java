package com.uca.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.uca.assignment.dbone.repositories.DBOneCustomerRepository;
import com.uca.assignment.dbone.services.DBOneCustomerService;
import com.uca.assignment.dbtwo.repositories.DBTwoCustomerRepository;
import com.uca.assignment.dbtwo.services.DBTwoCustomerService;
import com.uca.assignment.dtos.CustomerDto;
import com.uca.assignment.utili.services.MakeCustomerResponse;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTest {

	@Autowired
	private DBOneCustomerService customerService;

	@Autowired
	private DBOneCustomerRepository customerRepository;

	@Autowired
	private DBTwoCustomerService customerServiceDBTwo;

	@Autowired
	private DBTwoCustomerRepository customerRepositoryDBTwo;

	private String commonIC = "53845h3344vvsd";
	private String commonCxName = "Customer One";

	@Test
	@Order(1)
	@DisplayName("Make customer in db one test")
	public void makeCustomerTestDBOne() {
		CustomerDto makeCustomer = new CustomerDto();
		makeCustomer.setIc(commonIC);
		makeCustomer.setCxName(commonCxName);
		makeCustomer.setCxAge(34);
		makeCustomer.setCxDOB(new Date("1963/10/10"));

		MakeCustomerResponse madeCustomer = customerService.makeCustomer(makeCustomer);

		assertNotNull(madeCustomer);
		assertEquals(MakeCustomerResponse.Status.SUCCESS, madeCustomer.getStatus());

		assertNotEquals(makeCustomer.getIc(), madeCustomer.getCustomerDto().getIc());

		assertTrue(customerRepository.existsById(madeCustomer.getCustomerDto().getIc()));
		assertFalse(customerRepository.existsById(makeCustomer.getIc()));
	}

	@Test
	@Order(2)
	@DisplayName("Get by plain IC db one test")
	public void testGetByIc() {
		CustomerDto byIc = customerService.getByIc(commonIC);

		assertNotNull(byIc);
		assertEquals(commonCxName, byIc.getCxName());
	}

	@Test
	@Order(3)
	@DisplayName("Try to make existing customer in db one test")
	public void tryToMakeExistingCustomerTestDBOne() {
		CustomerDto makeCustomer = new CustomerDto();
		makeCustomer.setIc(commonIC);
		makeCustomer.setCxName(commonCxName);
		makeCustomer.setCxAge(34);
		makeCustomer.setCxDOB(new Date("1963/10/10"));

		MakeCustomerResponse madeCustomer = customerService.makeCustomer(makeCustomer);

		assertNotNull(madeCustomer);
		assertEquals(MakeCustomerResponse.Status.ALREADY_EXISTS, madeCustomer.getStatus());

		assertNull(madeCustomer.getCustomerDto());
	}

	@Test
	@Order(4)
	@DisplayName("Make customer in db two test")
	public void makeCustomerTestDBTwo() {
		CustomerDto makeCustomer = new CustomerDto();
		makeCustomer.setIc(commonIC);
		makeCustomer.setCxName("Customer One");
		makeCustomer.setCxAge(34);
		makeCustomer.setCxDOB(new Date("1963/10/10"));

		MakeCustomerResponse madeCustomer = customerServiceDBTwo.makeCustomer(makeCustomer);

		assertNotNull(madeCustomer);
		assertEquals(MakeCustomerResponse.Status.SUCCESS, madeCustomer.getStatus());

		assertNotEquals(makeCustomer.getIc(), madeCustomer.getCustomerDto().getIc());

		assertTrue(customerRepositoryDBTwo.existsById(madeCustomer.getCustomerDto().getIc()));
		assertFalse(customerRepositoryDBTwo.existsById(makeCustomer.getIc()));
	}

}
