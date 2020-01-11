package com.uca.assignment.utili.apis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public class BaseRestAPIRespose {

	private String status;

	private String message;

	@JsonInclude(Include.NON_NULL)
	private Object body;

	private BaseRestAPIRespose(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	private BaseRestAPIRespose(String status, String message, Object body) {
		super();
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public static ResponseEntity<?> createBaseRestAPIRespose(HttpStatus httpStatus, String meg) {
		return ResponseEntity.status(httpStatus).body(new BaseRestAPIRespose(httpStatus.name(), meg));
	}

	public static ResponseEntity<?> createBaseRestAPIRespose(HttpStatus httpStatus, String meg, Object obdy) {
		return ResponseEntity.status(httpStatus).body(new BaseRestAPIRespose(httpStatus.name(), meg, obdy));
	}

}
