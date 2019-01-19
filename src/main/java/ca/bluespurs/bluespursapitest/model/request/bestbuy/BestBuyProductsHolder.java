package ca.bluespurs.bluespursapitest.model.request.bestbuy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that will hold the list of products returned by the Best Buy search.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class BestBuyProductsHolder implements Serializable {
	private List<BestBuyProductDto> products;

	public List<BestBuyProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<BestBuyProductDto> products) {
		this.products = products;
	}
}
