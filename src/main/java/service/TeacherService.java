package service;

import java.util.List;

import model.Manager;
import model.Student;
import model.Subject;
import model.Teacher;

public interface TeacherService extends Service{
	
	//from service
	Object viewMyProfile(Object origin);
	boolean login(String id, String password);
	boolean forgotPassword(String id, String email);
	boolean changePassword(Object origin, String password);
	
	/// manager service
	void updateTeacher(String teacherId, String firstName, String lastName, boolean gender, String email, String passWord);
	
	String toString();
	
	List<Subject> viewSubject(String id);
	
	Teacher findTeacher(String id);
}
