package guru.springframework.controllers.constants;

import org.apache.logging.log4j.util.Strings;

public final class UrlConstants {

	public static final String INDEX_URL = "/";
	
	// Customers
	public static final String NEW_CUSTOMER_URL = "/customer/new";
	public static final String DELETE_CUSTOMER_URL = "/customer/delete/{id}";
	public static final String EDIT_CUSTOMER_URL = "/customer/edit/{id}";
	public static final String CUSTOMER_URL = "/customer";
	public static final String CUSTOMER_ID_URL = "/customer/{id}";
	public static final String CUSTOMERS_URL = "/customers";
	
	// Products
	public static final String NEW_PRODUCT_URL = "/product/new";
	public static final String DELETE_PRODUCT_URL = "/product/delete/{id}";
	public static final String EDIT_PRODUCT_URL = "/product/edit/{id}";
	public static final String PRODUCT_URL = "/product";
	public static final String PRODUCT_ID_URL = "/product/{id}";
	public static final String PRODUCTS_URL = "/products";
	
	public static String redirectTo(final String url) {
		return formatRedirect(url, Strings.EMPTY);
	}

	public static String redirectTo(final String url, final Integer pathVar) {
		return formatRedirect(url, pathVar.toString());
	}
	
	private static String formatRedirect(final String url, final String pathVar) {
		return String.format("redirect:%1$s/%2$s", url, pathVar); 
	}
}
