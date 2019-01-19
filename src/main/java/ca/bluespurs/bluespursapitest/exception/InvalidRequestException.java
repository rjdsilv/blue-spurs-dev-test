package ca.bluespurs.bluespursapitest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that will be thrown by the RESTful resource when the request sent is invalid.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException(String message) {
		super(message);
	}
}
