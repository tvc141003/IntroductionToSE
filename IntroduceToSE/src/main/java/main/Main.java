package main;




import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Student;

import utils.HibernateUtils;

public class Main {
	
	public static void main(String args[]) {
		SessionFactory factory = HibernateUtils.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		
		Student stu = new Student("STU01", "Le", "Trong", true, "LHT@gmail.com");
		
	//	StudentAccount account = new StudentAccount(stu, "123456");
		
		session.save(stu);
	//	session.save(account);
				
		
	

		
	
		
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	
	
}}
