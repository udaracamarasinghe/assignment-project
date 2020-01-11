package com.uca.assignment.dbone.services;

import com.uca.assignment.dtos.CustomerDto;

public class MakeCustomerResponse {

	public enum Status {
		SUCCESS, ALREADY_EXISTS
	}

	private Status status;

	private String message;

	private CustomerDto customerDto;

	public MakeCustomerResponse(Status status, String message, CustomerDto customerDto) {
		super();
		this.status = status;
		this.message = message;
		this.customerDto = customerDto;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

}
