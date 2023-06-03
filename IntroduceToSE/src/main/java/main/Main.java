package main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Student;
import model.Subject;
import model.Teacher;
import utils.HibernateUtils;

public class Main {
	
	public static void main(String args[]) {
		SessionFactory factory = HibernateUtils.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		
		Student student = new Student("STU01", "Le", "Trong", true);
		
		Teacher teacher = new Teacher("TEA01", "Tran", "Quang", true);
		
		
		Subject subject = new Subject("SUB01", "Software Enginerring", 4);
		
		Subject subject2 = new Subject("SUB02", "Data Science", 4);
		
		Set<Subject> set = new HashSet<Subject>();
		set.add(subject) ;
		set.add(subject2);
		
		student.setSubjects(set);
		teacher.setSubjects(set);
		
		
		
		
		session.save(student);
		session.save(subject);
		session.save(subject2);
		session.save(teacher);
		
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		
	}
	
}
