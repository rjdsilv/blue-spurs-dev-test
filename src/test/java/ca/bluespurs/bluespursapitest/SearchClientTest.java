package ca.bluespurs.bluespursapitest;

import ca.bluespurs.bluespursapitest.model.request.bestbuy.BestBuyProductsHolder;
import ca.bluespurs.bluespursapitest.model.response.ProductDto;
import ca.bluespurs.bluespursapitest.service.SearchClient;
import ca.bluespurs.bluespursapitest.service.SearchClientImpl;
import ca.bluespurs.bluespursapitest.service.exception.ObjectNotFoundException;
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
	public void searchBestBy_whenBestBuyIsCheaper() throws Exception {
		final String name = "ipad";
		final ProductDto product = searchClient.retrieveCheapestProduct(name);
		Assert.assertEquals(SearchClientImpl.BEST_BUY_LOC, product.getLocation());
	}

	@Test
	public void searchBestBy_whenWalmartIsCheaper() throws Exception {
		final String name = "lg monitor";
		final ProductDto product = searchClient.retrieveCheapestProduct(name);
		Assert.assertEquals(SearchClientImpl.WALMART_LOC, product.getLocation());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testProductRetrieval_whenProductNotFound() throws Exception {
		searchClient.retrieveCheapestProduct("asdfqwert");
	}
}
