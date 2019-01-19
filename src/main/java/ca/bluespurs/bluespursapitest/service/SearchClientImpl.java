package ca.bluespurs.bluespursapitest.service;

import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductDto;
import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductsHolder;
import ca.bluespurs.bluespursapitest.model.request.walmart.WalmartProductDto;
import ca.bluespurs.bluespursapitest.model.request.walmart.WalmartProductsHolder;
import ca.bluespurs.bluespursapitest.model.response.ProductDto;
import ca.bluespurs.bluespursapitest.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * Service class that will be used to perform REST calls to both Walmart and Best Buy APIs.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
@Service("searchClient")
public class SearchClientImpl implements SearchClient {
	public static final String BEST_BUY_LOC = "Best Buy";
	public static final String WALMART_LOC = "Walmart";

	private final static String WALMART_API_KEY = "rm25tyum3p9jm9x9x7zxshfa";
	private final static String WALMART_API_FMT = "http://api.walmartlabs.com/v1/search?apiKey=%s&query=%s&sort=price";

	private final static String BESTBUY_API_KEY = "pfe9fpy68yg28hvvma49sc89";
	private final static String BESTBUY_API_FORMAT = "https://api.bestbuy.com/v1/products(name=%s*)?apiKey=%s&sort=regularPrice.asc&show=name,salePrice&pageSize=1&format=json";

	private final RestTemplate restTemplate;

	/**
	 * Creates a new instance of SearchClientImpl using the given {@link RestTemplate}.
	 *
	 * @param restTemplate The {@link RestTemplate} to be used.
	 */
	@Autowired
	public SearchClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * @see SearchClient#retrieveCheapestProduct(String)
	 */
	@Override
	public ProductDto retrieveCheapestProduct(String name) throws ObjectNotFoundException {
		final List<BestBuyProductDto> bestBuyProducts = searchBestBuyProduct(name);
		final List<WalmartProductDto> walmartProducts = searchWalmartProduct(name);

		if ((Objects.isNull(bestBuyProducts) || bestBuyProducts.isEmpty()) &&
				(Objects.isNull(walmartProducts) || walmartProducts.isEmpty())) {
			throw new ObjectNotFoundException();
		}

		return retrieveCheapestProduct(bestBuyProducts, walmartProducts);
	}

	private BestBuyProductsHolder searchBestBuy(String name) {
		final String callUrl = String.format(BESTBUY_API_FORMAT, name, BESTBUY_API_KEY);
		final ResponseEntity<BestBuyProductsHolder> response = restTemplate.getForEntity(callUrl, BestBuyProductsHolder.class);
		return response.getBody();
	}

	private WalmartProductsHolder searchWalmart(String name) {
		final String callUrl = String.format(WALMART_API_FMT, WALMART_API_KEY, name);
		final ResponseEntity<WalmartProductsHolder> response = restTemplate.getForEntity(callUrl, WalmartProductsHolder.class);
		return response.getBody();
	}

	private List<BestBuyProductDto> searchBestBuyProduct(String name) {
		final BestBuyProductsHolder bestBuy = searchBestBuy(name);
		return bestBuy.getProducts();
	}

	private List<WalmartProductDto> searchWalmartProduct(String name) {
		final WalmartProductsHolder walmart = searchWalmart(name);
		return walmart.getItems();
	}

	private ProductDto retrieveCheapestProduct(List<BestBuyProductDto> bestBuyProducts,
	                                           List<WalmartProductDto> walmartProducts) {
		final ProductDto product = new ProductDto();

		if (bestBuyProducts.isEmpty() || walmartProducts.get(0).getSalePrice() < bestBuyProducts.get(0).getSalePrice()) {
			product.setProductName(walmartProducts.get(0).getName());
			product.setBestPrice(String.format("%.2f", walmartProducts.get(0).getSalePrice()));
			product.setLocation(WALMART_LOC);
		} else if (walmartProducts.isEmpty() || bestBuyProducts.get(0).getSalePrice() < walmartProducts.get(0).getSalePrice()) {
			product.setProductName(bestBuyProducts.get(0).getName());
			product.setBestPrice(String.format("%.2f", bestBuyProducts.get(0).getSalePrice()));
			product.setLocation(BEST_BUY_LOC);
		}

		return product;
	}
}
