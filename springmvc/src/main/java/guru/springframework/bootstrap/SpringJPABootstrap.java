package guru.springframework.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ProductService;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private static final Integer ELEMENTS_COUNT = 5;
	
	private ProductService productService;
	private CustomerService customerService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadProducts();
		loadCustomers();
	}
	
	public void loadProducts() {
		for(int i = 0; i < ELEMENTS_COUNT; i++) {
			Product product = createProduct(i + 1);
			productService.createOrUpdateObject(product);
		}
	}
	
	public void loadCustomers() {
		for(int i = 0; i < ELEMENTS_COUNT; i++) {
			Customer customer = createCustomer(i + 1);
			customerService.createOrUpdateObject(customer);
		}
	}
	
	private Product createProduct(final Integer productId) {
		final Product product = new Product();
        product.setId(productId);
        product.setDescription(String.format("Product %1$s", productId));
        product.setPrice(new BigDecimal("12.99"));
        product.setImageUrl(String.format("http://example.com/product%1$s", productId));
        
        return product;
	}
	
	private Customer createCustomer(final Integer customerId) {
		final Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName(String.format("Customer %1$02d", customerId));
    	customer.setLastName(String.format("Customer %1$02d LN", customerId));
    	customer.setEmail(String.format("customer%1$s@domain.com", customerId));
    	customer.setPhoneNumber("3214569870");
    	customer.setAddressLineOne("Address line one");
    	customer.setAddressLineTwo("Address line two");
    	customer.setCity("City"); 
    	customer.setState("State");
    	customer.setZipCode("111705");
        
        return customer;
	}
	
}
