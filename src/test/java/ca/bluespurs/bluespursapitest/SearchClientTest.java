package ca.bluespurs.bluespursapitest;

import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductsHolder;
import ca.bluespurs.bluespursapitest.service.SearchClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for the {@link ca.bluespurs.bluespursapitest.service.SearchClient} service.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchClientTest {
	@Autowired
	private SearchClient searchClient;

	@Test
	public void searchBestBy_whenProductFound() {
		final String name = "ipad";
		final BestBuyProductsHolder productsHolder = searchClient.searchBestBuy(name);
		Assert.assertNotNull(productsHolder);
		Assert.assertNotNull(productsHolder.getProducts());
		Assert.assertFalse(productsHolder.getProducts().isEmpty());
		Assert.assertTrue(productsHolder.getProducts().get(0).getName().toLowerCase().contains(name));
	}

	@Test
	public void searchBestBy_whenProductNotFound() {
		final String name = "asdfqwert";
		final BestBuyProductsHolder productsHolder = searchClient.searchBestBuy(name);
		Assert.assertNotNull(productsHolder);
		Assert.assertNotNull(productsHolder.getProducts());
		Assert.assertTrue(productsHolder.getProducts().isEmpty());
	}
}
