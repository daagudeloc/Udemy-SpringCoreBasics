package guru.springframework.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Customer;
import guru.springframework.domain.DomainObject;

@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public Customer getObjectById(Integer id) {
		return (Customer) super.getObjectById(id);
	}
	
	@Override
	public Customer createOrUpdateObject(Customer domainObject) {
		return (Customer) super.createOrUpdateObject(domainObject);
	}
	
	@Override
	public void deleteObject(Integer id) {
		super.deleteObject(id);
	}
	
	@Override
	protected void loadDomainObjects() {
        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        
    	customer1.setId(1);
    	customer1.setFirstName("Customer 01");
    	customer1.setLastName("Customer 01 LN");
    	customer1.setEmail("customer01@domain.com");
    	customer1.setPhoneNumber("3214569870");
    	customer1.setAddressLineOne("Address line one");
    	customer1.setAddressLineTwo("Address line two");
    	customer1.setCity("City"); 
    	customer1.setState("State");
    	customer1.setZipCode("111705");

        domainMap.put(1, customer1);

        Customer customer2 = new Customer();
        
        customer2.setId(2);
        customer2.setFirstName("Customer 02");
        customer2.setLastName("Customer 02 LN");
        customer2.setEmail("customer02@domain.com");
        customer2.setPhoneNumber("3214569870");
        customer2.setAddressLineOne("Address line one");
        customer2.setAddressLineTwo("Address line two");
        customer2.setCity("City"); 
        customer2.setState("State");
        customer2.setZipCode("111705");
        
        domainMap.put(2, customer2);
        
        Customer customer3 = new Customer();
        
        customer3.setId(3);
        customer3.setFirstName("Customer 03");
        customer3.setLastName("Customer 03 LN");
        customer3.setEmail("customer03@domain.com");
        customer3.setPhoneNumber("3214569870");
        customer3.setAddressLineOne("Address line one");
        customer3.setAddressLineTwo("Address line two");
        customer3.setCity("City"); 
        customer3.setState("State");
        customer3.setZipCode("111705");
        
        domainMap.put(3, customer3);
        
        Customer customer4 = new Customer();
        
        customer4.setId(4);
        customer4.setFirstName("Customer 04");
        customer4.setLastName("Customer 04 LN");
        customer4.setEmail("customer04@domain.com");
        customer4.setPhoneNumber("3214569870");
        customer4.setAddressLineOne("Address line one");
        customer4.setAddressLineTwo("Address line two");
        customer4.setCity("City"); 
        customer4.setState("State");
        customer4.setZipCode("111705");
        
        domainMap.put(4, customer4);
        
        Customer customer5 = new Customer();
        
        customer5.setId(5);
        customer5.setFirstName("Customer 05");
        customer5.setLastName("Customer 05 LN");
        customer5.setEmail("customer05@domain.com");
        customer5.setPhoneNumber("3214569870");
        customer5.setAddressLineOne("Address line one");
        customer5.setAddressLineTwo("Address line two");
        customer5.setCity("City"); 
        customer5.setState("State");
        customer5.setZipCode("111705");
        
        domainMap.put(5, customer5);

    }
	
}
