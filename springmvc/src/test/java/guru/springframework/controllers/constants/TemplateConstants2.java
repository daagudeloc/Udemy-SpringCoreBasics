package guru.springframework.controllers.constants;

public final class TemplateConstants2 {
	
	public static final String INDEX = "index";
	public static final String CUSTOMER_FORM = "customer-form";
	public static final String CUSTOMER = "customer";
	public static final String CUSTOMERS = "customers";
	public static final String PRODUCT_FORM = "product-form";
	public static final String PRODUCTS = "products";
	public static final String PRODUCT = "product";
		
	public static String getCustomerTemplate(final String template) {
		return formatTemplateString(CUSTOMER, template);
	}

	public static String getProductTemplate(final String template) {
		return formatTemplateString(PRODUCT, template);
	}
	
	private static String formatTemplateString(final String templateType, final String templateName) {
		return String.format("%1s/%2s", templateType, templateName);
	}
}
