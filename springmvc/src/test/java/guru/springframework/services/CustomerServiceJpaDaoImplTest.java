package guru.springframework.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceJpaDaoImplTest {
	
	private static int customerCount = 5;
	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Test
	public void shouldReturnCustomerList() {
		
		System.out.println(String.format("Test for list of customers: Customer count is (%1$d)", customerCount));
		final List<Customer> customers = (List<Customer>) customerService.listAll();
		System.out.println(String.format("Customer list size is (%1$d)", customers.size()));
		assertThat(customers.size(), is(customerCount));
	}

	@Test
	public void shouldReturnSpecificCustomer() {
		
		final Customer customer = customerService.getObjectById(1);
		
		System.out.println("Test for specific customer");
		assertThat(customer.getId(), is(1));
		assertThat(customer.getFirstName(), is("Customer 01"));
	}

	@Test
	public void shouldCreateCustomer() {
		
		System.out.println(String.format("Test for create new customer: Currently, there are (%1$d) Customers", customerCount));
		final Customer product = createCustomer(++customerCount);
		customerService.createOrUpdateObject(product);
		final List<Customer> customers = (List<Customer>) customerService.listAll();
		
		System.out.println(String.format("After the test, there are (%1$d) Customers", customers.size()));
		
		assertThat(customers.size(), is(customerCount));
	}

	@Test
	public void shouldUpdateCustomer() {
		
		Customer product = customerService.getObjectById(2);
		product.setFirstName("New first name");
		customerService.createOrUpdateObject(product);
		Customer retrievedCustomerFromDb = customerService.getObjectById(2);
		
		System.out.println("Test for specific customer");
		assertThat(product.getFirstName(), is(retrievedCustomerFromDb.getFirstName()));
	}
	
	@Test
	public void shouldDeleteCustomer() {
		System.out.println(String.format("Test for delete a customer: Currently, there are (%1$d) Customers", customerCount));
		customerService.deleteObject(customerCount);
	
		final List<Customer> customers = (List<Customer>) customerService.listAll();
		System.out.println(String.format("After the test, there are (%1$d) Customers", customers.size()));
		
		assertThat(--customerCount, is(customers.size()));
	}
	
	private void loadCustomers(final Integer customerCount) {
		
		for(int i = 0; i < customerCount; i++) {
			customerService.createOrUpdateObject(createCustomer(i + 1));
		}
        System.out.println("Customers loaded!!");
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
