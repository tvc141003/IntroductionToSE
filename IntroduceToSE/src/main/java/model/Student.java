package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	
	
	public Student() {
		
		
	}

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
	
	
	


	public Set<Student_Subject> getStudent_subject() {
		return student_subject;
	}

	public void setStudent_subject(Set<Student_Subject> student_subject) {
		this.student_subject = student_subject;
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
	
	@OneToMany(mappedBy = "student")
	Set<Student_Subject> student_subject;
	
	
	
	
}
