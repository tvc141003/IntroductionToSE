package service;

import java.util.List;


import model.Subject;

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
	boolean create(Object origin);
	boolean delete (Object origin);
	
}
