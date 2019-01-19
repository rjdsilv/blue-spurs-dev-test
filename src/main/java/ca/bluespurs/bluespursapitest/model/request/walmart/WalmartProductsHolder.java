package ca.bluespurs.bluespursapitest.model.request.walmart;

import java.io.Serializable;
import java.util.List;

/**
 * Class that will hold the list of products returned by the Best Buy search.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class WalmartProductsHolder implements Serializable {
	private List<WalmartProductDto> items;

	public List<WalmartProductDto> getItems() {
		return items;
	}

	public void setItems(List<WalmartProductDto> items) {
		this.items = items;
	}
}
