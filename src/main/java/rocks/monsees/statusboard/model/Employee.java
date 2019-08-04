package rocks.monsees.statusboard.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.hibernate.validator.internal.util.privilegedactions.NewSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Employee {

	private static final Logger logger = LoggerFactory.getLogger(Employee.class);

	@Id
	@GeneratedValue
	private int id;
	private String position; // used for login
	private String title;
	private String name;
	private String password;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(value="begin")
	private List<Status> statusList = new ArrayList<Status>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Authority", joinColumns = @JoinColumn(name = "id"))
	@Enumerated(EnumType.STRING)
	private Set<Authority> authorities = new HashSet<>();

	public Employee() {
		super();
	}

	public Employee(String position, String title, String name, String password, Set<Authority> authorities) {
		super();
		this.position = position;
		this.title = title;
		this.name = name;
		this.password = password;
		this.authorities = authorities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public void setStatus(Status status) {
//		this.status = status;
//	}
//
//	public Status getStatus() {
//		return this.status;
//	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void addAuthority(Authority authority) {
		authorities.add(authority);
	}

	public void removeAuthority(Authority authority) {
		authorities.remove(authority);
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
		for(Status s: statusList) {
			s.setEmployee(this);
		}
	}

	public void addStatus(Status status) {
		this.statusList.add(status);
		status.setEmployee(this);
		Collections.sort(this.statusList); //makes no sense cause persistence provider decides how to store data
	}

	public Status getCurrentStatus() {
		if(this.statusList.size()==0)
			return new Status(LocalDate.of(9999, 1, 1), LocalDate.of(9999, 12, 1), false, "no status set");
		return this.statusList.get(0);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", position=" + position + ", title=" + title + ", name=" + name + ", password="
				+ password + ", statusList=" + statusList + ", authorities=" + authorities + "]";
	}

}
