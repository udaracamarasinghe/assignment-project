package com.uca.assignment.dbtwo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uca.assignment.dbtwo.entities.DBTwoCustomer;
import com.uca.assignment.dbtwo.repositories.DBTwoCustomerRepository;
import com.uca.assignment.dtos.CustomerDto;
import com.uca.assignment.utili.services.ConverterService;
import com.uca.assignment.utili.services.DeleteCustomerServiceResponse;
import com.uca.assignment.utili.services.HashingService;
import com.uca.assignment.utili.services.MakeCustomerResponse;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class DBTwoCustomerService {

	@Autowired
	private DBTwoCustomerRepository customerRepository;

	@Autowired
	private ConverterService converterService;

	@Autowired
	private HashingService hashingService;

	public List<CustomerDto> getAll() {
		List<DBTwoCustomer> customers = customerRepository.findAll();

		return customers.stream().map(converterService::convertToDtoDBTwo).collect(Collectors.toList());
	}

	public CustomerDto getByIc(String ic) {
		String hashedIC = hashingService.hash(ic);

		Optional<DBTwoCustomer> opCus = customerRepository.findById(hashedIC);

		if (opCus.isPresent()) {
			return converterService.convertToDtoDBTwo(opCus.get());
		}

		return null;
	}

	public CustomerDto getByHashedIc(String hashedIC) {
		Optional<DBTwoCustomer> opCus = customerRepository.findById(hashedIC);

		if (opCus.isPresent()) {
			return converterService.convertToDtoDBTwo(opCus.get());
		}

		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public MakeCustomerResponse makeCustomer(CustomerDto makeCustomer) {
		String hashedIC = hashingService.hash(makeCustomer.getIc());

		if (customerRepository.existsById(hashedIC)) {
			return new MakeCustomerResponse(MakeCustomerResponse.Status.ALREADY_EXISTS,
					"Customer already exists with ic: " + makeCustomer.getIc(), null);
		}

		DBTwoCustomer customer = converterService.convertToDtoDBTwo(makeCustomer);
		customer.setIc(hashedIC);

		DBTwoCustomer madeCustomer = customerRepository.save(customer);

		return new MakeCustomerResponse(MakeCustomerResponse.Status.SUCCESS, "Customer made successfuly.",
				converterService.convertToDtoDBTwo(madeCustomer));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public DeleteCustomerServiceResponse deleteCustomer(String deleteByIc) {
		if (!customerRepository.existsById(deleteByIc)) {
			return new DeleteCustomerServiceResponse(DeleteCustomerServiceResponse.Status.NOT_EXISTS,
					"Customer not exists with ic: " + deleteByIc);
		}

		customerRepository.deleteById(deleteByIc);

		return new DeleteCustomerServiceResponse(DeleteCustomerServiceResponse.Status.SUCCESS,
				"Customer deleted successfuly.");
	}
}
