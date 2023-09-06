package service;

import java.util.List;

import dataValidation.userValidation.emailChecking.GmailFormCheckingImpl;
import dataValidation.userValidation.genderChecking.GenderCheckingImpl;
import dataValidation.userValidation.idChecking.*;
import dataValidation.userValidation.nameChecking.CharacterCheckingImpl;
import dataValidation.userValidation.nameChecking.LengthCheckingImpl;
import model.Manager;
import model.ManagerAccount;
import model.Subject;
import model.Teacher;
import model.TeacherAccount;
import model.Student;
import model.StudentAccount;
import repository.AccountRepository;
import repository.ManagerAccountRepositoryImpl;
import repository.ManagerRepositoryImpl;
import repository.SubjectRepositoryImpl;
import repository.TeacherAccountRepositoryImpl;
import repository.TeacherRepositoryImpl;
import repository.StudentAccountRepositoryImpl;
import repository.StudentRepositoryImpl;
import utils.MailUtils;

public class StudentServiceImpl implements StudentService{

	private GmailFormCheckingImpl mail;
	private static StudentServiceImpl INSTANCE;
	private StudentServiceImpl() {

	}

	public static StudentServiceImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new StudentServiceImpl();
		}
		return INSTANCE;
	}
	@Override
	public Object viewMyProfile(Object origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String id, String password) {
		if(StudentAccountRepositoryImpl.getInstance().isCorrect(id, password) == true)
		{
			System.out.print("true login");
			return true;
		}
		else
		{
			System.out.print("false login");
			return false;
			
		}
	}

	@Override
	public boolean forgotPassword(String id, String email) {
		mail = new GmailFormCheckingImpl();
		if (mail.check(email) == true && email.equals(StudentRepositoryImpl.getInstance().findById(id).getEmail())) {
			String password = "";
			password = StudentAccountRepositoryImpl.getInstance().findByUsernameId(id).getPassword();

			MailUtils.getInstance().sendPassword(email, password);
			System.out.print(password);
			return true;
		} else {
			System.out.print("Email error");
			return false;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public boolean changePassword(Object origin, String password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void updateStudent(String studentId, String firstName, String lastName, boolean gender, String email,String passWord) {
		// TODO Auto-generated method stub
		StudentAccountRepositoryImpl.getInstance().remove(studentId);
		StudentRepositoryImpl.getInstance().remove(studentId);
		Student student = new Student(studentId, firstName, lastName, gender, email);
		StudentRepositoryImpl.getInstance().save(student);
		StudentAccount studentAccount = new StudentAccount(student, passWord);
		StudentAccountRepositoryImpl.getInstance().save(studentAccount);
	}

	@Override
	public List<Subject> viewSubject(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findStudent(String id) {
		return StudentRepositoryImpl.getInstance().findById(id);
	}
	
}