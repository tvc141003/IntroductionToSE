package main;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Manager;
import model.ManagerAccount;
import model.Student;
import model.StudentAccount;
import repository.AccountRepository;
import repository.ManagerAccountRepositoryImpl;
import repository.StudentAccountRepositoryImpl;
import repository.StudentRepositoryImpl;
import utils.HibernateUtils;
import utils.MailUtils;

public class Main {

	public static void main(String args[]) {
		SessionFactory factory = HibernateUtils.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();

//
//		Student std = new Student("S002", "Truong", "Van Chi", true);
//		StudentAccount stdAccount = new StudentAccount(std, "123123");
//		
//		session.save(std);
//		session.save(stdAccount);
	
		
		Student std = StudentRepositoryImpl.getInstance().findById("S002");
		
		
		System.out.print(std.getFirstName());
		
		
		StudentAccountRepositoryImpl stdSelect = StudentAccountRepositoryImpl.getInstance();
		stdSelect.changePassword(std.getAccount(), "122233456");
		
		System.out.print(stdSelect.isCorrect("S002", "122233456"));
		try {
//			session.flush();
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace(); 
			
		}
		session.close();
	}
}
