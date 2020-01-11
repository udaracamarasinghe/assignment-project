package com.uca.assignment.utili.apis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@ControllerAdvice
public class GlobleExceptionHandler {

	private Logger logger = LogManager.getLogger(GlobleExceptionHandler.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(Exception e) {
		logger.error(e.getMessage(), e);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Object() {
			public String status = "500";
			public String message = e.getMessage();
		});
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		logger.error(e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
			public String status = HttpStatus.BAD_REQUEST.name();
			public String message = e.getMessage();
		});
	}
}
