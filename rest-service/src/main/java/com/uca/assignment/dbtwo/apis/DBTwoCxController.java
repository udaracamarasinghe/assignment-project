package com.uca.assignment.dbtwo.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.assignment.dbtwo.services.DBTwoCustomerService;
import com.uca.assignment.dtos.CustomerDto;
import com.uca.assignment.utili.apis.BaseRestAPIRespose;
import com.uca.assignment.utili.services.DeleteCustomerServiceResponse;
import com.uca.assignment.utili.services.MakeCustomerResponse;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@RestController
@RequestMapping("/db-two-customer")
public class DBTwoCxController {

	@Autowired
	private DBTwoCustomerService customerService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.OK, "Existing all customers",
				customerService.getAll());
	}

	@GetMapping("/find/{ic}")
	public ResponseEntity<?> getByIc(@PathVariable("ic") String ic) {
		CustomerDto byIc = customerService.getByIc(ic);

		if (byIc != null) {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.FOUND, "Customer found with ic:" + ic, byIc);
		} else {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.NOT_FOUND,
					"Customer not found with ic: " + ic, null);
		}
	}

	@GetMapping("/{ic}")
	public ResponseEntity<?> getByHashedIc(@PathVariable("ic") String ic) {
		CustomerDto byIc = customerService.getByHashedIc(ic);

		if (byIc != null) {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.FOUND, "Customer found with ic:" + ic, byIc);
		} else {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.NOT_FOUND,
					"Customer not found with ic: " + ic, null);
		}
	}

	@PostMapping
	public ResponseEntity<?> makeCustomer(@RequestBody CustomerDto makeCustomer) {
		MakeCustomerResponse makeCustomerResponse = customerService.makeCustomer(makeCustomer);

		switch (makeCustomerResponse.getStatus()) {
		case SUCCESS: {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.CREATED, makeCustomerResponse.getMessage(),
					makeCustomerResponse.getCustomerDto());
		}
		case ALREADY_EXISTS: {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.BAD_REQUEST,
					makeCustomerResponse.getMessage(), null);
		}
		default: {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.NOT_MODIFIED,
					makeCustomerResponse.getMessage(), null);
		}
		}
	}

	@DeleteMapping("{ic}")
	public ResponseEntity<?> deleteByIc(@PathVariable("ic") String deleteByIc) {
		DeleteCustomerServiceResponse deleteCustomerServiceResponse = customerService.deleteCustomer(deleteByIc);

		switch (deleteCustomerServiceResponse.getStatus()) {
		case SUCCESS: {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.OK,
					deleteCustomerServiceResponse.getMessage(), null);
		}
		case NOT_EXISTS: {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.BAD_REQUEST,
					deleteCustomerServiceResponse.getMessage(), null);
		}
		default: {
			return BaseRestAPIRespose.createBaseRestAPIRespose(HttpStatus.NOT_MODIFIED,
					deleteCustomerServiceResponse.getMessage(), null);
		}
		}
	}

}
