package ca.bluespurs.bluespursapitest.common;

/**
 * Class containing all the application's available resources.
 */
public interface RestResources {
	String PRODUCT = "product";
	String PRODUCT_SEARCH = "search";
	String PRODUCT_SEARCH_FULL = String.format("/%s/%s", PRODUCT, PRODUCT_SEARCH);
}
