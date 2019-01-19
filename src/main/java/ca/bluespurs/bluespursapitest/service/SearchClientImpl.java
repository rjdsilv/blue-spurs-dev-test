package ca.bluespurs.bluespursapitest.service;

import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductsHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class that will be used to perform REST calls to both Walmart and Best Buy APIs.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
@Service("searchClient")
public class SearchClientImpl implements SearchClient {
	private final static String WALMART_API_KEY = "rm25tyum3p9jm9x9x7zxshfa";
	private final static String WALMART_API_FMT = "http://api.walmartlabs.com/v1/search?apiKey=%s&query=%s&sort=price";

	private final static String BESTBUY_API_KEY = "pfe9fpy68yg28hvvma49sc89";
	private final static String BESTBUY_API_FORMAT = "https://api.bestbuy.com/v1/products(name=%s*)?apiKey=%s&sort=salePrice.dsc&show=name,regularPrice,salePrice&pageSize=1&format=json";

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
	 * @see SearchClient#searchBestBuy(String)
	 */
	@Override
	public BestBuyProductsHolder searchBestBuy(String name) {
		final String callUrl = String.format(BESTBUY_API_FORMAT, name, BESTBUY_API_KEY);
		final ResponseEntity<BestBuyProductsHolder> response = restTemplate.getForEntity(callUrl, BestBuyProductsHolder.class);
		return response.getBody();
	}
}
