package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {

	public Teacher() {

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

	@OneToMany(mappedBy = "teacher")
	Set<Teacher_Subject> teacher_subject ;
	

}
