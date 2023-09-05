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
@Table(name = "teacher_account")
public class TeacherAccount {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@OneToOne(fetch = FetchType.EAGER,cascade =  CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "teacher_id", unique = true)
	private Teacher teacher;
	
	
	@Column(name ="password")
	String password;


	public TeacherAccount(Teacher teacher, String passWord) {
		// TODO Auto-generated constructor 
		this.teacher = teacher;
		this.password = passWord;
	}
	public TeacherAccount() {
		
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}


	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
