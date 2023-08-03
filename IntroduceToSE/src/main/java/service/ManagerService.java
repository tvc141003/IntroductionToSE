package service;

import java.util.List;

import model.Manager;
import model.Student;
import model.Subject;
import model.Teacher;

public interface ManagerService extends Service{
	
	//from service
	Object viewMyProfile(Object origin);
	boolean login(String id, String password);
	boolean forgotPassword(String id, String email);
	boolean changePassword(Object origin, String password);
	boolean joinSubject(Subject subject, Object origin );
	boolean outSubject(Subject subject, Object origin);
	List<Subject> viewSubject(Subject subject, Object origin);
	
	/// manager service
	boolean createManager(String managerId, String firstName, String lastName, boolean gender, String email, String passWord);
	boolean deleteManager(Object origin);
	boolean updateManager(String managerId, String firstName, String lastName, boolean gender, String email, String passWord);
	
	boolean createSubject(String subjectId, String name, int credits );
	boolean deleteSubject(Object origin);
	boolean updateSubject(String subjectId, String name, int credits );
	
	boolean createTeacher(String teacherId, String firstName, String lastName, boolean gender, String email, long id, String passWord);
	boolean deleteTeacher(Object origin);
	boolean updateTeacher(String teacherId, String firstName, String lastName, boolean gender, String email, String passWord);
	
	boolean createStudent(String studentId, String firstName, String lastName, boolean gender, String email, long id, String passWord);
	boolean deleteStudent(Object origin);
	boolean updateStudent(String studentId, String firstName, String lastName, boolean gender, String email, String passWord);
	
	List<Manager> viewManager();
	List<Teacher> viewTeacher();
	List<Student> viewStudent();
	List<Subject> viewSubject();
	
	Manager findManager(String id);
	Teacher findTeacher(String id);
	Student findStudent(String id);
	Subject findSubject(String id);
}
