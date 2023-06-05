package model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Teacher_Subject {

	
	
	
	
	
	
	
	public Teacher_Subject() {
		
	}
	
	public Teacher_Subject(Teacher teacher, Subject subject_id, int semester, String className) {
		super();
		this.teacher = teacher;
		this.subject_id = subject_id;
		this.semester = semester;
		this.className = className;
	}

	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Subject getSubject_id() {
		return subject_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSubject_id(Subject subject_id) {
		this.subject_id = subject_id;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
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
	@JoinColumn(name = "teacher_id")
	Teacher teacher;
	
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	Subject subject_id;
	
	int semester;
	
	String className;
}
