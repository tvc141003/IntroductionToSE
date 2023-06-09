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
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id")
	private Manager manager;
	
	
	@Column(name ="password")
	String password;
}
