package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {

	public Teacher() {

	}

	public Teacher(String id, String firstName, String lastName, boolean gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Set<Subject> getSubjects() {
		return teacher_subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.teacher_subjects = subjects;
	}

	@Id
	@Column(name = "teacher_id")
	private String id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private boolean gender;

	
	 @ManyToMany
	 @JoinTable( name = "teacher_subject", joinColumns = {
	 @JoinColumn(name="teacher_id") }, inverseJoinColumns = {
	 @JoinColumn(name="subject_id") }

	  ) Set<Subject> teacher_subjects;
	

}
