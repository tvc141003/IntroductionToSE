package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	
	

	public Student(String studentId, String firstName, String lastName, boolean gender, String email) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}

	public Student(String studentId, String firstName, String lastName, boolean gender) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	public Student() {
		
		
	}

	public String getStudentId() {
		return studentId;
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





	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}





	public StudentAccount getAccount() {
		return account;
	}

	public void setAccount(StudentAccount account) {
		this.account = account;
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
	
	@Column(name = "email",unique = true)
	private String email ;
	
	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
	Set<Student_Subject> student_subject;
	
	@OneToOne( mappedBy = "student", fetch = FetchType.EAGER)
	private StudentAccount account ;
	
	
	
}
