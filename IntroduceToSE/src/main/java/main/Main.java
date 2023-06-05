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
		
		
		StudentRepositoryImpl stuRepos = StudentRepositoryImpl.getInstance();
		
		List<Student> list = stuRepos.findAll();
		
		Student student = list.get(0);
		
		
		Set<Student_Subject> set = student.getStudent_subject();
		
		for (Student_Subject s : set) {
			System.out.println(s.getSubject().getName());
		}
		
		
	

		
	
		
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	
	
}}
