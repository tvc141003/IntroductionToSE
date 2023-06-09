package main;




import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import utils.HibernateUtils;
import utils.MailUtils;

public class Main {
	
	public static void main(String args[]) {
		SessionFactory factory = HibernateUtils.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Student std = new Student("STU05", "Le", "Nguyen", true);
		
<<<<<<< HEAD
		MailUtils mailUtils = MailUtils.getInstance();
		
		mailUtils.sendEmail("lehuutrong141@gmail.com", "hello world");
		
	
=======
		Subject sub = new Subject("SUB09", "Machine learning", 4);
		
		session.save(std);
		session.save(sub);
>>>>>>> ca5e81dafc0c0f8dd469c0f97f71e77ab0eedda2
		
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	
	
}}
