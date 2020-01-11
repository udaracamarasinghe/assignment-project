package com.uca.assignment.dbone.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uca.assignment.dbone.entities.DBOneCustomer;
import com.uca.assignment.dbone.repositories.DBOneCustomerRepository;
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
public class DBOneCustomerService {

	@Autowired
	private DBOneCustomerRepository customerRepository;

	@Autowired
	private ConverterService converterService;

	@Autowired
	private HashingService hashingService;

	public List<CustomerDto> getAll() {
		List<DBOneCustomer> customers = customerRepository.findAll();

		return customers.stream().map(converterService::convertToDtoDBOne).collect(Collectors.toList());
	}

	public CustomerDto getByIc(String ic) {
		String hashedIC = hashingService.hash(ic);

		Optional<DBOneCustomer> opCus = customerRepository.findById(hashedIC);

		if (opCus.isPresent()) {
			return converterService.convertToDtoDBOne(opCus.get());
		}

		return null;
	}

	public CustomerDto getByHashedIc(String hashedIC) {
		Optional<DBOneCustomer> opCus = customerRepository.findById(hashedIC);

		if (opCus.isPresent()) {
			return converterService.convertToDtoDBOne(opCus.get());
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

		DBOneCustomer customer = converterService.convertToDtoDBOne(makeCustomer);
		customer.setIc(hashedIC);

		DBOneCustomer madeCustomer = customerRepository.save(customer);

		return new MakeCustomerResponse(MakeCustomerResponse.Status.SUCCESS, "Customer made successfuly.",
				converterService.convertToDtoDBOne(madeCustomer));
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
