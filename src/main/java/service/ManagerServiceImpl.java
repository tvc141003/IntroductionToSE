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
		System.out.print(id);
		mail = new GmailFormCheckingImpl();
		System.out.print(email);
		if (mail.check(email) == true && email.equals(ManagerRepositoryImpl.getInstance().findById(id).getEmail())) {
			System.out.print(email);
			String password = "";
			password = ManagerAccountRepositoryImpl.getInstance().findByUsernameId(id).getPassword();
			System.out.print(password);
			MailUtils.getInstance().sendPassword(email, password);
			System.out.print("1111");
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
	public boolean updateManager(String managerId, String firstName, String lastName, boolean gender, String email, String passWord) {
		ManagerAccountRepositoryImpl.getInstance().remove(managerId);
		ManagerRepositoryImpl.getInstance().remove(managerId);
		
		Manager manager = new Manager(managerId, firstName, lastName, gender, email);
		ManagerRepositoryImpl.getInstance().save(manager);
		ManagerAccount managerAccount = new ManagerAccount(manager, passWord);
		ManagerAccountRepositoryImpl.getInstance().save(managerAccount);
		System.out.print(manager.getManagerId());
		return true;
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
		SubjectRepositoryImpl.getInstance().remove(subjectId);
		
		Subject subject = new Subject(subjectId, name, credits);
		SubjectRepositoryImpl.getInstance().save(subject);
		return true;
	}

	@Override
	public boolean createTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		TeacherRepositoryImpl.getInstance().save(teacher);
		String password = teacher.getId() + teacher.getFirstName();
		TeacherAccount teacherAccount = new TeacherAccount(teacher, password);
		TeacherAccountRepositoryImpl.getInstance().save(teacherAccount);
		return true;
		
	}

	@Override
	public boolean deleteTeacher(Object origin) {
		Teacher teacher = (Teacher) origin;
		TeacherAccountRepositoryImpl.getInstance().remove(teacher.getId());
		TeacherRepositoryImpl.getInstance().remove(teacher.getId());
		return true;
	}

	@Override
	public boolean updateTeacher(String teacherId, String firstName, String lastName, boolean gender, String email,String passWord) {
		TeacherAccountRepositoryImpl.getInstance().remove(teacherId);
		TeacherRepositoryImpl.getInstance().remove(teacherId);
		
		Teacher  teacher = new Teacher(teacherId, firstName, lastName, gender, email);
		TeacherRepositoryImpl.getInstance().save(teacher);
		TeacherAccount teacherAccount = new TeacherAccount(teacher, passWord);
		TeacherAccountRepositoryImpl.getInstance().save(teacherAccount);
		return true;
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
		Student student = (Student) origin;
		StudentAccountRepositoryImpl.getInstance().remove(student.getStudentId());
		StudentRepositoryImpl.getInstance().remove(student.getStudentId());
		return true;
	}

	@Override
	public boolean updateStudent(String studentId, String firstName, String lastName, boolean gender, String email,String passWord) {
		StudentAccountRepositoryImpl.getInstance().remove(studentId);
		StudentRepositoryImpl.getInstance().remove(studentId);
		Student student = new Student(studentId, firstName, lastName, gender, email);
		StudentRepositoryImpl.getInstance().save(student);
		StudentAccount studentAccount = new StudentAccount(student, passWord);
		StudentAccountRepositoryImpl.getInstance().save(studentAccount);
		return true;
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