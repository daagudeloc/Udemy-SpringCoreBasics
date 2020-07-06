package guru.springframework.services.jpaservices;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class AbstractJpaDaoService {

	protected EntityManagerFactory factory;

	@PersistenceUnit
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
}
