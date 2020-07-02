package guru.springframework.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Customer;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl implements CustomerService {

	private EntityManagerFactory factory;

	@PersistenceUnit
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
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
	public Customer createOrUpdateObject(final Customer productToSave) {
		final EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		Customer modifiedCustomer = manager.merge(productToSave); 
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
