package guru.springframework.services.jpaservices;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import guru.springframework.services.security.EncryptionService;

@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

	private EncryptionService encryptionService;
	
	@Autowired	
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@Override
	public List<User> listAll() {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.createQuery("from User", User.class).getResultList();
	}

	@Override
	public User getObjectById(final Integer id) {
		final EntityManager manager = factory.createEntityManager();
		
		return manager.find(User.class, id);
	}

	@Override
	public User createOrUpdateObject(final User userToModify) {
		final EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();

		if(Objects.nonNull(userToModify.getPassword())) {
			userToModify.setEncryptedPassword(encryptionService.encryptString(userToModify.getPassword()));
		}
		
		User modifiedUser = manager.merge(userToModify);
		manager.getTransaction().commit();
		
		return modifiedUser;
	}

	@Override
	public void deleteObject(final Integer id) {
		final EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		
		User productToRemove = manager.find(User.class, id);
		
		manager.remove(productToRemove);
		manager.getTransaction().commit();
		
	}
	
}
