package ca.bluespurs.bluespursapitest.model.request.bestbuy;

import java.io.Serializable;

/**
 * Product class that will be used to return the product found at Best Buy.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class BestBuyProductDto implements Serializable {
	private String name;
	private double salePrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
}
