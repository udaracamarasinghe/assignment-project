package com.uca.assignment.utili.services;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public class DeleteCustomerServiceResponse {

	public enum Status {
		SUCCESS, NOT_EXISTS
	}

	private Status status;

	private String message;

	public DeleteCustomerServiceResponse(Status status, String message) {
		super();
		this.status = status;
		this.message = message;
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

}
