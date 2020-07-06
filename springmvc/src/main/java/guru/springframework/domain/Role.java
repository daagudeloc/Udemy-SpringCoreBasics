package guru.springframework.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role extends AbstractDomainClass {
	
	private String role;
	
	@ManyToMany
	@JoinTable
	private List<User> users = new ArrayList<>();

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(final User user) {
		if (!users.contains(user)) {
			users.add(user);
		}
		
		if (!user.getRoles().contains(this)) {
			user.getRoles().add(this);
		}
	}
	
	public void removeUser(final User user) {
		users.remove(user);
		user.getRoles().remove(this);
	}
}
