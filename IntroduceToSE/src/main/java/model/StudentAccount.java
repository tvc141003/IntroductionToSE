package model;

import javax.persistence.CascadeType;
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
	
	
	public long getId() {
		return id;
	}


	public StudentAccount() {
		super();
	}


	public StudentAccount(Student student, String password) {
		super();
		this.student = student;
		this.password = password;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@OneToOne(fetch = FetchType.EAGER,cascade =  CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "student_id", unique = true)
	private Student student;
	 
	
	@Column(name = "password")
	private String password;
	
}
