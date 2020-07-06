package guru.springframework.services.jpaservices;

import static java.util.Objects.nonNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import guru.springframework.services.security.EncryptionService;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {

	private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

	@Override
	public List<Customer> listAll() {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.createQuery("from Customer", Customer.class).getResultList();
	}

	@Override
	public Customer getObjectById(final Integer id) {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.find(Customer.class, id);
	}

	@Override
	public Customer createOrUpdateObject(final Customer customerToSave) {
		final EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		if (nonNull(customerToSave.getUser()) && nonNull(customerToSave.getUser().getPassword())) {
			customerToSave.getUser().setEncryptedPassword(
					encryptionService.encryptString(customerToSave.getUser().getPassword()));
		}
		
		Customer modifiedCustomer = manager.merge(customerToSave); 
		manager.getTransaction().commit();
		
		return modifiedCustomer;
	}

	@Override
	public void deleteObject(final Integer id) {
		final EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		
		Customer productToRemove = manager.find(Customer.class, id);
		
		manager.remove(productToRemove);
		manager.getTransaction().commit();
		
	}
	
}
