package ca.bluespurs.bluespursapitest.rest.server;

import ca.bluespurs.bluespursapitest.common.RestResources;
import ca.bluespurs.bluespursapitest.exception.InvalidRequestException;
import ca.bluespurs.bluespursapitest.exception.ProductNotFoundException;
import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductDto;
import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductsHolder;
import ca.bluespurs.bluespursapitest.model.response.ProductDto;
import ca.bluespurs.bluespursapitest.service.SearchClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	private final String BEST_BUY_LOC = "Best Buy";

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
	@GetMapping(value = RestResources.PRODUCT_SEARCH)
	public ResponseEntity<ProductDto> search(@RequestParam("name") String name) {
		// The name must containg a value.
		if (StringUtils.isBlank(name)) {
			throw new InvalidRequestException("The parameter name cannot be empty or null!");
		}

		// No product could be found.
		final BestBuyProductsHolder bestBuy = searchClient.searchBestBuy(name);
		final List<BestBuyProductDto> bestBuyProducts = bestBuy.getProducts();
		if (bestBuyProducts.isEmpty()) {
			throw new ProductNotFoundException(name);
		}

		final ProductDto product = new ProductDto();
		product.setProductName(bestBuyProducts.get(0).getName());
		product.setBestPrice(String.format("%.2f", bestBuyProducts.get(0).getRegularPrice()));
		product.setLocation(BEST_BUY_LOC);
		return ResponseEntity.ok(product);
	}
}
