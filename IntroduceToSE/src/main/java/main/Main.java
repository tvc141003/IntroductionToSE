package main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Student;
import model.Student_Subject;
import model.Subject;
import model.Teacher;
import repository.StudentRepositoryImpl;
import utils.HibernateUtils;

public class Main {
	
	public static void main(String args[]) {
		SessionFactory factory = HibernateUtils.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Student std = new Student("STU05", "Le", "Nguyen", true);
		
		Subject sub = new Subject("SUB09", "Machine learning", 4);
		
		session.save(std);
		session.save(sub);
		
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	
	
}}
