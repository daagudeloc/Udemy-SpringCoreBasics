package guru.springframework.services.mapservices;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Role;
import guru.springframework.services.RoleService;

@Service
@Profile("map")
public class RoleServiceImpl extends AbstractMapService implements RoleService{
	
	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public Role getObjectById(Integer id) {
		return (Role) super.getObjectById(id);
	}
	
	@Override
	public Role createOrUpdateObject(Role domainObject) {
		return (Role) super.createOrUpdateObject(domainObject);
	}
	
	@Override
	public void deleteObject(Integer id) {
		super.deleteObject(id);
	}
	
}
