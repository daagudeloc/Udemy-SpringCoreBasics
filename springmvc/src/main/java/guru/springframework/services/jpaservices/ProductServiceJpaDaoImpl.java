package guru.springframework.services.jpaservices;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductService {

	@Override
	public List<Product> listAll() {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public Product getObjectById(final Integer id) {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.find(Product.class, id);
	}

	@Override
	public Product createOrUpdateObject(final Product productToSave) {
		final EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		Product modifiedProduct = manager.merge(productToSave); 
		manager.getTransaction().commit();
		
		return modifiedProduct;
	}

	@Override
	public void deleteObject(final Integer id) {
		final EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		
		Product productToRemove = manager.find(Product.class, id);
		
		manager.remove(productToRemove);
		manager.getTransaction().commit();
		
	}
	
}
