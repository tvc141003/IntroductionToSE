package service;

import java.util.List;

import model.Manager;
import model.Student;
import model.Subject;
import model.Teacher;

public interface StudentService extends Service{
	
	//from service
	Object viewMyProfile(Object origin);
	boolean login(String id, String password);
	boolean forgotPassword(String id, String email);
	boolean changePassword(Object origin, String password);
	boolean joinSubject(Subject subject, Object origin );
	boolean outSubject(Subject subject, Object origin);
	List<Subject> viewSubject(Subject subject, Object origin);
	
	/// manager service
	void updateStudent(String studentId, String firstName, String lastName, boolean gender, String email, String passWord);
	
	String toString();
	
	List<Subject> viewSubject(String id);
	
	Student findStudent(String id);
}
