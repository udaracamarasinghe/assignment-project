package com.uca.assignment.dbone.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uca.assignment.dbone.entities.Customer;
import com.uca.assignment.dbone.repositories.CustomerRepository;
import com.uca.assignment.dbone.services.MakeCustomerResponse.Status;
import com.uca.assignment.dtos.CustomerDto;
import com.uca.assignment.utili.services.ConverterService;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ConverterService converterService;

	public List<CustomerDto> getAll() {
		List<Customer> customers = customerRepository.findAll();

		return customers.stream().map(converterService::convertToDto).collect(Collectors.toList());
	}

	public CustomerDto getByIc(String ic) {
//		if (ic.equals("error"))
//			throw new RuntimeException("Thrwed exception");

		Optional<Customer> opCus = customerRepository.findById(ic);

		if (opCus.isPresent()) {
			return converterService.convertToDto(opCus.get());
		}

		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public MakeCustomerResponse makeCustomer(CustomerDto makeCustomer) {
		if (customerRepository.existsById(makeCustomer.getIc())) {
			return new MakeCustomerResponse(MakeCustomerResponse.Status.ALREADY_EXISTS,
					"Customer already exists with ic: " + makeCustomer.getIc(), null);
		}

		Customer customer = converterService.convertToDto(makeCustomer);

		Customer madeCustomer = customerRepository.save(customer);

		return new MakeCustomerResponse(MakeCustomerResponse.Status.SUCCESS, "Customer made successfuly.",
				converterService.convertToDto(madeCustomer));
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
