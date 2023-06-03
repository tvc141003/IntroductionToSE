package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Student;
import model.Subject;
import utils.HibernateUtils;

public class Main {
	
	public static void main(String args[]) {
		SessionFactory factory = HibernateUtils.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx= session.beginTransaction() ;
		
		Student student = new Student("STU01", "Le", "Trong", true);
		
		Subject subject = new Subject("SUB01", "Software Engineering", 4);
		
		session.save(student);
		session.save(subject);
		
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}

		
		
	}
	
}
