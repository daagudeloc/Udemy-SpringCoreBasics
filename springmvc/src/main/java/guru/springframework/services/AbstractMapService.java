package guru.springframework.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import guru.springframework.domain.DomainObject;

public abstract class AbstractMapService {
	protected Map<Integer,DomainObject> domainMap;
	
	public AbstractMapService() {
		domainMap = new HashMap<Integer, DomainObject>();
	}
	
	public List<DomainObject> listAll() {
		return new ArrayList<>(domainMap.values()); 
	}
	
	public DomainObject getObjectById(Integer id) {
		return domainMap.get(id);
	}
	
	public DomainObject createOrUpdateObject(DomainObject domainObject) {
		if(Objects.isNull(domainObject))
			throw new RuntimeException("Product can't be null.");
		
		if(Objects.isNull(domainObject.getId()))
			domainObject.setId(getNextKey());
		
		domainMap.put(domainObject.getId(), domainObject);
		
		return domainObject;
	}
	
	public void deleteObject(Integer id) {
		domainMap.remove(id);
	}
	
	private Integer getNextKey() {
		return Collections.max(domainMap.keySet()) + 1;
	}
	
}
