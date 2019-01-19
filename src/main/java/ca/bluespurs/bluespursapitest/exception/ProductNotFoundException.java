package ca.bluespurs.bluespursapitest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that will be thrown by the RESTful resource when the product searched is not found.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String name) {
		super(String.format("The product %s could not be found!", name));
	}
}
