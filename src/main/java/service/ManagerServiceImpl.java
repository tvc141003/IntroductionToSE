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

public class ManagerServiceImpl implements ManagerService {
	private static ManagerServiceImpl INSTANCE;
	private GmailFormCheckingImpl mail;
	private GenderCheckingImpl gender;
	private LengthCheckingImpl checkLengthName;
	private LengthCheckingImpl checkLengthId;

	private ManagerServiceImpl() {

	}

	public static ManagerServiceImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManagerServiceImpl();
		}
		return INSTANCE;
	}

	public boolean login(String id, String password) {
		if (ManagerAccountRepositoryImpl.getInstance().isCorrect(id, password) == true) {
			System.out.println("true Login");
			return true;
		} else {
			System.out.println("false Login");
			return false;
		}

	}

	@Override
	public Object viewMyProfile(Object origin) {
		Manager manager = (Manager) origin;
		// System.out.print(ManagerAccountRepositoryImpl.getInstance().findByUsernameId(manager.getManagerId()));
		ManagerAccount n = (ManagerAccount) ManagerAccountRepositoryImpl.getInstance()
				.findByUsernameId(manager.getManagerId());
		System.out.print(n.getId() + " " + n.getPassword() + " " + n.getManager().getEmail());
		return n;
	}

	@Override
	public boolean forgotPassword(String id, String email) {
		mail = new GmailFormCheckingImpl();
		if (mail.check(email) == true) {
			String password = "";
			password = ManagerAccountRepositoryImpl.getInstance().findByUsernameId(id).getPassword();

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
		ManagerAccount manager = (ManagerAccount) origin;
		ManagerAccountRepositoryImpl.getInstance().changePassword(manager, password);
		System.out.print(manager.getPassword());
		return true;
	}

	@Override
	public boolean joinSubject(Subject subject, Object origin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean outSubject(Subject subject, Object origin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Subject> viewSubject(Subject subject, Object origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createManager(Manager manager) {
		// TODO Auto-generated method stub
		ManagerRepositoryImpl.getInstance().save(manager);
		String password = manager.getManagerId() + manager.getFirstName();
		ManagerAccount managerAccount = new ManagerAccount(manager, password);
		ManagerAccountRepositoryImpl.getInstance().save(managerAccount);
		return true;
		/*
		 * mail = new GmailFormCheckingImpl(); checkLengthName = new
		 * LengthCheckingImpl(); if(mail.check(manager.getEmail()) == true) { //Manager
		 * manager = new Manager(managerId,firstName,lastName,gender,email);
		 * ManagerRepositoryImpl.getInstance().save(manager);
		 * 
		 * ManagerAccount managerAccount = new ManagerAccount(manager,passWord);
		 * ManagerAccountRepositoryImpl.getInstance().save(managerAccount);
		 * 
		 * return true; } return false;
		 */
	}

	@Override
	public boolean deleteManager(Object origin) {
		Manager manager = (Manager) origin;
		ManagerAccountRepositoryImpl.getInstance().remove(manager.getManagerId());
		ManagerRepositoryImpl.getInstance().remove(manager.getManagerId());
		return true;
	}

	@Override
	public boolean updateManager(String managerId, String firstName, String lastName, boolean gender, String email,
			String passWord) {
		return false;
	}

	@Override
	public boolean createSubject(String subjectId, String name, int credits) {
		Subject subject = new Subject(subjectId, name, credits);
		SubjectRepositoryImpl.getInstance().save(subject);
		return true;
	}

	@Override
	public boolean deleteSubject(Object origin) {
		Subject subject = (Subject) origin;
		SubjectRepositoryImpl.getInstance().remove(subject.getSubjectId());
		return true;
	}

	@Override
	public boolean updateSubject(String subjectId, String name, int credits) {
		return false;
	}

	@Override
	public boolean createTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		TeacherRepositoryImpl.getInstance().save(teacher);
		String password = teacher.getId() + teacher.getFirstName();
		TeacherAccount teacherAccount = new TeacherAccount(Long.parseLong(teacher.getId()),teacher, password);
		TeacherAccountRepositoryImpl.getInstance().save(teacherAccount);
		/*
		 * TeacherAccount teacherAccount = new TeacherAccount(id, teacher, passWord);
		 * TeacherAccountRepositoryImpl.getInstance().save(teacherAccount);
		 */
		return true;
		
	}

	@Override
	public boolean deleteTeacher(Object origin) {
		TeacherAccount teacher = (TeacherAccount) origin;
		TeacherAccountRepositoryImpl.getInstance().remove(teacher.getTeacher().getId());
		TeacherRepositoryImpl.getInstance().remove(teacher.getTeacher().getId());
		return true;
	}

	@Override
	public boolean updateTeacher(String teacherId, String firstName, String lastName, boolean gender, String email,
			String passWord) {
		return false;
	}

	@Override
	public boolean createStudent(Student student) {
		// TODO Auto-generated method stub
		StudentRepositoryImpl.getInstance().save(student);
		String password = student.getStudentId() + student.getFirstName();
		StudentAccount studentAccount = new StudentAccount(student, password);
		StudentAccountRepositoryImpl.getInstance().save(studentAccount);
		return true;
	}

	@Override
	public boolean deleteStudent(Object origin) {
		StudentAccount studentAccount = (StudentAccount) origin;
		StudentAccountRepositoryImpl.getInstance().remove(studentAccount.getStudent().getStudentId());
		StudentRepositoryImpl.getInstance().remove(studentAccount.getStudent().getStudentId());
		return true;
	}

	@Override
	public boolean updateStudent(String studentId, String firstName, String lastName, boolean gender, String email,
			String passWord) {
		return false;
	}

	@Override
	public List<Manager> viewManager() {
		// TODO Auto-generated method stub
		return ManagerRepositoryImpl.getInstance().findAll();
	}

	@Override
	public List<Teacher> viewTeacher() {
		// TODO Auto-generated method stub
		return TeacherRepositoryImpl.getInstance().findAll();
	}

	@Override
	public List<Student> viewStudent() {
		// TODO Auto-generated method stub
		return StudentRepositoryImpl.getInstance().findAll();
	}

	@Override	
	public List<Subject> viewSubject() {
		// TODO Auto-generated method stub
		return SubjectRepositoryImpl.getInstance().findAll();
	}

	@Override
	public Manager findManager(String id) {
		return ManagerRepositoryImpl.getInstance().findById(id);
	}

	@Override
	public Teacher findTeacher(String id) {
		// TODO Auto-generated method stub
		return TeacherRepositoryImpl.getInstance().findById(id);
	}

	@Override
	public Student findStudent(String id) {
		// TODO Auto-generated method stub
		return StudentRepositoryImpl.getInstance().findById(id);
	}

	@Override
	public Subject findSubject(String id) {
		// TODO Auto-generated method stub
		return SubjectRepositoryImpl.getInstance().findById(id);
	}

	@Override
	public String toString() {
		return "ManagerServiceImpl";
	}
}