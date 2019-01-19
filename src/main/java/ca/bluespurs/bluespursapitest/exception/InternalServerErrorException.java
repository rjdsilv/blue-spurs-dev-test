package ca.bluespurs.bluespursapitest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that will be thrown by the RESTful resource when some unknown exception occurs.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {
	public InternalServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}
