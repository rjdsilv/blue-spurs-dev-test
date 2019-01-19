package ca.bluespurs.bluespursapitest.service;

import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductsHolder;

/**
 * Interface containing the client search methods for searching for products in both Wallmart and Best Buy.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public interface SearchClient {
	/**
	 * Searches for the the cheapest product matching the given name. As the best buy accepts the page size and sorting,
	 * we are using these two features together to return only the cheapest product using the API by sorting by the
	 * product's sale price in descending order and having the page size as 1.
	 *
	 * @param name The name to be searched.
	 * @return The cheapest product matching the name.
	 */
	BestBuyProductsHolder searchBestBuy(String name);
}
