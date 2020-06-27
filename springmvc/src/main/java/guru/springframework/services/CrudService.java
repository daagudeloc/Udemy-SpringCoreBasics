package guru.springframework.services;

import java.util.List;

public interface CrudService<T> {
	
	List<?> listAll();
	T getObjectById(Integer id);
	T createOrUpdateObject(T domainObject);
	void deleteObject(Integer id);

}
