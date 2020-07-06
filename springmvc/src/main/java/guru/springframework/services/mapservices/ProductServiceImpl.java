package guru.springframework.services.mapservices;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public Product getObjectById(Integer id) {
		return (Product) super.getObjectById(id);
	}
	
	@Override
	public Product createOrUpdateObject(Product domainObject) {
		return (Product) super.createOrUpdateObject(domainObject);
	}
	
	@Override
	public void deleteObject(Integer id) {
		super.deleteObject(id);
	}
	
}
