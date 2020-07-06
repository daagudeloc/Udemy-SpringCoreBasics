package guru.springframework.services.mapservices;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;

@Service
@Profile("map")
public class UserServiceImpl extends AbstractMapService implements UserService {

	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public User getObjectById(Integer id) {
		return (User) super.getObjectById(id);
	}
	
	@Override
	public User createOrUpdateObject(User domainObject) {
		return (User) super.createOrUpdateObject(domainObject);
	}
	
	@Override
	public void deleteObject(Integer id) {
		super.deleteObject(id);
	}
	
}
