package ca.bluespurs.bluespursapitest.rest.server;

import ca.bluespurs.bluespursapitest.common.RestResources;
import ca.bluespurs.bluespursapitest.exception.InternalServerErrorException;
import ca.bluespurs.bluespursapitest.exception.InvalidRequestException;
import ca.bluespurs.bluespursapitest.exception.ProductNotFoundException;
import ca.bluespurs.bluespursapitest.model.response.ProductDto;
import ca.bluespurs.bluespursapitest.service.SearchClient;
import ca.bluespurs.bluespursapitest.service.exception.ObjectNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller that will receive the calls in order to search for a product with the given name in both Walmart and
 * Best Buy APIs.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
@RestController
@RequestMapping(RestResources.PRODUCT)
public class ProductSearchRest {
	private static final Logger LOGGER = LogManager.getLogger(ProductSearchRest.class);

	private final SearchClient searchClient;

	/**
	 * Creates a new instance of the search API.
	 *
	 * @param searchClient The search client to be used.
	 */
	@Autowired
	public ProductSearchRest(SearchClient searchClient) {
		this.searchClient = searchClient;
	}

	/**
	 * Resource method that will be used to search products in both Walmart and Best Buy by the given name.
	 *
	 * @param name The name to be used in the search.
	 * @return A response containing the product with the best price matching the name.
	 */
	@GetMapping(value = RestResources.PRODUCT_SEARCH, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
	public ResponseEntity<ProductDto> search(@RequestParam(value = "name") String name) {
		// The name must containing a value.
		if (StringUtils.isBlank(name)) {
			final InvalidRequestException ex = new InvalidRequestException("The parameter name cannot be empty or null!");
			LOGGER.error("The name cannot be blank!", ex);
			throw ex;
		}

		try {
			final ProductDto product = searchClient.retrieveCheapestProduct(name);
			LOGGER.info("Search Successful! Returning product: " + product.toString());
			return ResponseEntity.ok(product);
		} catch(ObjectNotFoundException ex) {
			// If no product is found
			LOGGER.error("The product with name " + name + " could not be found!", ex);
			throw new ProductNotFoundException(name);
		} catch (Exception ex) {
			// Rethrows the caught exception.
			if (ex instanceof ProductNotFoundException) {
				throw ex;
			}
			LOGGER.error("Unexpected error: " + ex.getMessage(), ex);
			throw new InternalServerErrorException(ex.getMessage(), ex);
		}
	}
}
