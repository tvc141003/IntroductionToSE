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
@Table(name = "student")
public class Student {
	
	
	
	public Student() {
		
		
	}
	
//	

	public String getStudentId() {
		return studentId;
	}

	public Student(String studentId, String firstName, String lastName, boolean gender) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	@Column(name = "student_id")
	private String studentId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "gender")
	private boolean gender;
	
	@ManyToMany
	@JoinTable(
			name = "student_subject",
			joinColumns = {
					@JoinColumn(name="student_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name="subject_id")
			}
			
			)
	Set<Subject> subjects;
	
	
	
}
