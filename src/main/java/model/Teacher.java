package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {

	public Teacher() {

	}
	public Teacher(String teacherId, String firstName, String lastName, boolean gender, String email) {
		super();
		this.teacherId = teacherId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}
	public Teacher(String id, String firstName, String lastName, boolean gender) {
		super();
		this.teacherId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public String getId() {
		return teacherId;
	}

	public void setId(String id) {
		this.teacherId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}


	@Id
	@Column(name = "teacher_id")
	private String teacherId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private boolean gender;
	
	@Column(name = "email",unique = true)
	private String email ;

	@OneToMany(mappedBy = "teacher",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	Set<Teacher_Subject> teacher_subject ;

	@OneToOne(mappedBy = "teacher")
	private TeacherAccount account;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Teacher_Subject> getTeacher_subject() {
		return teacher_subject;
	}

	public void setTeacher_subject(Set<Teacher_Subject> teacher_subject) {
		this.teacher_subject = teacher_subject;
	}
	/**
	 * @return the account
	 */
	public TeacherAccount getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(TeacherAccount account) {
		this.account = account;
	}
	

}
