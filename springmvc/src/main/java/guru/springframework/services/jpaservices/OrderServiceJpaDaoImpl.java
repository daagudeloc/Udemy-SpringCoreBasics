package guru.springframework.services.jpaservices;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Order;
import guru.springframework.services.OrderService;

@Service
@Profile("jpadao")
public class OrderServiceJpaDaoImpl extends AbstractJpaDaoService implements OrderService {

	@Override
	public List<Order> listAll() {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.createQuery("from Order", Order.class).getResultList();
	}

	@Override
	public Order getObjectById(final Integer id) {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.find(Order.class, id);
	}

	@Override
	public Order createOrUpdateObject(final Order domainObject) {
		final EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		final Order savedOrder = manager.merge(domainObject);
		manager.getTransaction().commit();
		
		return savedOrder;
	}

	@Override
	public void deleteObject(final Integer id) {
		final EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		manager.merge(manager.find(Order.class, id));
		manager.getTransaction().commit();
	}

}
