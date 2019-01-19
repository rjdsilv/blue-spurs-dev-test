package ca.bluespurs.bluespursapitest.model.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Product class that will be used to return the product found on the given search.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class ProductDto implements Serializable {
	private final String currency = "CAD";

	private String productName;
	private String bestPrice;
	private String location;

	public String getCurrency() {
		return currency;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBestPrice() {
		return bestPrice;
	}

	public void setBestPrice(String bestPrice) {
		this.bestPrice = bestPrice;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("currency", currency)
				.append("productName", productName)
				.append("bestPrice", bestPrice)
				.append("location", location)
				.toString();
	}
}
