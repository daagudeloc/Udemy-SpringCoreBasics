package guru.springframework.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
public class User implements DomainObject {
	
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer id;
	
	@Version
	private Integer version;
	
	private String username;
	
	@Transient
	private String password;
	
	private String encryptedPassword;
	private Boolean enabled = true;
	
	@OneToOne(cascade = {MERGE, PERSIST})
	private Customer customer;
	
	@OneToOne(cascade = ALL, orphanRemoval = true)
    private Cart cart;
	
	@ManyToMany
	@JoinTable
	private List<Role> roles = new ArrayList<>();

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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		customer.setUser(this);
	}
	
	public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
	
    public void addRole(final Role role) {
    	if (!roles.contains(role)) {
			roles.add(role);
		}
    	
    	if(!role.getUsers().contains(this)) {
    		role.getUsers().add(this);
    	}
    }
    
    public void removeRole(final Role role) {
    	roles.remove(role);
    	role.getUsers().remove(this);
    }
    
}
