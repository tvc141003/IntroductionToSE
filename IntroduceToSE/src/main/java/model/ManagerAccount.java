package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "manager_account")
public class ManagerAccount {
	
	
	public ManagerAccount(Manager manager, String password) {
		super();
		this.manager = manager;
		this.password = password;
	}


	public Manager getManager() {
		return manager;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setManager(Manager manager) {
		this.manager = manager;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ManagerAccount() {
		super();
	}


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id", unique = true)
	private Manager manager;
	
	
	@Column(name ="password")
	String password;
}
