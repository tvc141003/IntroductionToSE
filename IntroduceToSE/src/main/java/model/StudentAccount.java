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
@Table(name ="student_account")
public class StudentAccount {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private Student student;
	 
	
	@Column(name = "password")
	private String password;
	
}
