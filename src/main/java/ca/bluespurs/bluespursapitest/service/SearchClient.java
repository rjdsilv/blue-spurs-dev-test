package ca.bluespurs.bluespursapitest.service;

import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductsHolder;
import ca.bluespurs.bluespursapitest.model.request.walmart.WalmartProductsHolder;
import ca.bluespurs.bluespursapitest.model.response.ProductDto;
import ca.bluespurs.bluespursapitest.service.exception.ObjectNotFoundException;

/**
 * Interface containing the client search methods for searching for products in both Wallmart and Best Buy.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public interface SearchClient {
	/**
	 * Method that will retrieve the cheapest product with the given name.
	 *
	 * @param name The product to be found
	 * @return The cheapest product.
	 * @throws ObjectNotFoundException if no product with the given name can be found.
	 */
	ProductDto retrieveCheapestProduct(String name) throws ObjectNotFoundException;
}
