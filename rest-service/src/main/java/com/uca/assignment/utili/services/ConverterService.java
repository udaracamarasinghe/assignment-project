package com.uca.assignment.utili.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.assignment.dbone.entities.Customer;
import com.uca.assignment.dtos.CustomerDto;

@Service
public class ConverterService {

	@Autowired
	private ModelMapper modelMapper;

	public CustomerDto convertToDto(Customer post) {
		CustomerDto customerDto = modelMapper.map(post, CustomerDto.class);
		// customerDto.setSubmissionDate(post.getSubmissionDate(),
		// userService.getCurrentUser().getPreference().getTimezone());
		return customerDto;
	}

	public Customer convertToDto(CustomerDto makeCustomer) {
		Customer customer = modelMapper.map(makeCustomer, Customer.class);
		return customer;
	}
}
