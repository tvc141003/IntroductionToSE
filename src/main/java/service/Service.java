package service;

import java.util.List;

import model.Manager;
import model.Subject;

public interface Service {
	Object viewMyProfile(Object origin);
	boolean login(String id, String password);
	boolean forgotPassword(String id, String email);
	boolean changePassword(Object origin, String password);
}
