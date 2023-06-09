package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student_Subject {
	
	
	
	
	public Student_Subject() {
		
	}
	
	
	public Student_Subject(Student student, Subject subject, String semester, String className) {
		super();
		this.student = student;
		this.subject = subject;
		this.semester = semester;
		this.className = className;
	}

	
	
	
	public long getId() {
		return id;
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


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public String getSemester() {
		return semester;
	}


	public void setSemester(String semester) {
		this.semester = semester;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	Student student;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	Subject subject;
	
	String semester;
	
	String className;


	
}
