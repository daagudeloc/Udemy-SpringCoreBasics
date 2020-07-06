package guru.springframework.services.jpaservices;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Role;
import guru.springframework.services.RoleService;

@Service
@Profile("jpadao")
public class RoleServiceJpaDaoImpl extends AbstractJpaDaoService implements RoleService{

	@Override
	public List<Role> listAll() {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.createQuery("from Role", Role.class).getResultList();
	}

	@Override
	public Role getObjectById(final Integer id) {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.find(Role.class, id);
	}

	@Override
	public Role createOrUpdateObject(final Role domainObject) {
		final EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		final Role savedRole = manager.merge(domainObject);
		manager.getTransaction().commit();
		
		return savedRole;
	}

	@Override
	public void deleteObject(final Integer id) {
		final EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		
		Role roleToRemove = manager.find(Role.class, id);
		
		manager.remove(roleToRemove);
		manager.getTransaction().commit();
	}

	
	
}
