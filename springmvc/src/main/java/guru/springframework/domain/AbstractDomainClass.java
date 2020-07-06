package guru.springframework.domain;

import static java.util.Objects.isNull;
import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractDomainClass implements DomainObject {

	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer id;
	
	@Version
	private Integer version;
	
	private Date dateCreated;
	private Date lastUpdated;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@PreUpdate
	@PrePersist
	public void updateTimeStamp() {
		lastUpdated = new Date();
		if (isNull(dateCreated)) {
			dateCreated = new Date();
		}
	}
	
}
