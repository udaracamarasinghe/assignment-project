package com.uca.assignment.utili.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.assignment.dbone.entities.DBOneCustomer;
import com.uca.assignment.dbtwo.entities.DBTwoCustomer;
import com.uca.assignment.dtos.CustomerDto;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class ConverterService {

	@Autowired
	private ModelMapper modelMapper;

	public CustomerDto convertToDtoDBOne(DBOneCustomer post) {
		CustomerDto customerDto = modelMapper.map(post, CustomerDto.class);
		// customerDto.setSubmissionDate(post.getSubmissionDate(),
		// userService.getCurrentUser().getPreference().getTimezone());
		return customerDto;
	}

	public DBOneCustomer convertToDtoDBOne(CustomerDto makeCustomer) {
		DBOneCustomer customer = modelMapper.map(makeCustomer, DBOneCustomer.class);
		return customer;
	}

	public CustomerDto convertToDtoDBTwo(DBTwoCustomer post) {
		CustomerDto customerDto = modelMapper.map(post, CustomerDto.class);
		// customerDto.setSubmissionDate(post.getSubmissionDate(),
		// userService.getCurrentUser().getPreference().getTimezone());
		return customerDto;
	}

	public DBTwoCustomer convertToDtoDBTwo(CustomerDto makeCustomer) {
		DBTwoCustomer customer = modelMapper.map(makeCustomer, DBTwoCustomer.class);
		return customer;
	}
}
