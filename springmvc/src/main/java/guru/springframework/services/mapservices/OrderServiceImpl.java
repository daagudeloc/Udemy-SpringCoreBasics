package guru.springframework.services.mapservices;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Order;
import guru.springframework.services.OrderService;

@Service
@Profile("map")
public class OrderServiceImpl extends AbstractMapService implements OrderService {

	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public Order getObjectById(Integer id) {
		return (Order) super.getObjectById(id);
	}
	
	@Override
	public Order createOrUpdateObject(Order domainObject) {
		return (Order) super.createOrUpdateObject(domainObject);
	}
	
	@Override
	public void deleteObject(Integer id) {
		super.deleteObject(id);
	}

}
