package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name  = "subject")
public class Subject {

	
	
	public String getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCredits() {
		return credits;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Subject() {
		
	}
	
	public Subject(String subjectId, String name, int credits) {
		super();
		this.subjectId = subjectId;
		this.name = name;
		this.credits = credits;
	}
	

	public Set<Student> getStudents() {
		return students;
	}


	public void setStudents(Set<Student> students) {
		this.students = students;
	}


	public Set<Teacher> getTeachers() {
		return teachers;
	}


	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	

	@Id
	@Column(name = "subject_id")
	private String subjectId ;
	
	@Column(name = "name")
	private String name ;
	
	@Column(name = "credits")
	private int credits;
	
	
	@ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
	Set<Student> students;
	
	@ManyToMany(mappedBy = "teacher_subjects", fetch = FetchType.EAGER)
	Set<Teacher> teachers;
	
}
