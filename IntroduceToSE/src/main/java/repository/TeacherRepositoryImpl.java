package repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import model.Teacher;

import utils.HibernateUtils;


public class TeacherRepositoryImpl implements TeacherRepository {
	

	private static Root<Teacher> root;
	private static Session session;
	private static CriteriaBuilder cb;
	private static SessionFactory factory = HibernateUtils.getSessionFactory();
	
	
	public List<Teacher> findAll() {
		session = factory.openSession(); // open session
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Teacher> cr = cb.createQuery(Teacher.class);
		root = cr.from(Teacher.class);
		
		cr.select(root) ;
		org.hibernate.query.Query<Teacher> query = session.createQuery(cr);
		List<Teacher> list  = query.getResultList();
		session.close(); // close session
		if (list.size() == 0 ) 
			return null;
		return list;
		
	}

	public Teacher findById(String id) {
		session = factory.openSession(); // open session
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Teacher> cr = cb.createQuery(Teacher.class);
		root = cr.from(Teacher.class);
		
		cr.select(root).where(cb.equal(root.get("teacherId"), id));
		org.hibernate.query.Query<Teacher> query = session.createQuery(cr);
		List<Teacher> list  = query.getResultList();
		session.close(); // close session;
		
		if (list.size() == 0 ) 
			return null;
		Teacher teacher = list.get(0);
		return teacher;
	}

	public void save( Teacher model) {
		session = factory.openSession();
		
		session.save(model);
		
		Transaction tx = session.beginTransaction();
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); // close session
		}
		
		
	}

	public void remove(String id) {
		Teacher teacher = this.findById(id);
		if (teacher == null) return ;
		
		session = factory.openSession(); //open session
		
		session.delete(teacher);
		
		
		Transaction tx = session.beginTransaction();
		
		
		
		
	
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); // close session
		}
		
	}

	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Teacher findByName(String name) {
		
		session = factory.openSession(); // open session
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Teacher> cr = cb.createQuery(Teacher.class);
		root = cr.from(Teacher.class);
		
		Predicate inFirstName = cb.like(((Expression)root.get("first_name")),"%" + name +"%");
		Predicate inLastName = cb.like(((Expression)root.get("last_name")),"%" + name +"%") ;
		
		cr.select(root).where(cb.or(inFirstName,inLastName));
		
		org.hibernate.query.Query<Teacher> query = session.createQuery(cr);
		
		
		List<Teacher> list  = query.getResultList();
		session.close(); // close session
		
		if (list.size() == 0 ) 
			return null;
		Teacher teacher = list.get(0);
		return teacher;
	}






	private static  TeacherRepositoryImpl INSTANCE ;
	private TeacherRepositoryImpl() {
		
	}
	
	
	public static TeacherRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TeacherRepositoryImpl();
			
		}
		return INSTANCE;
	}
	
	
	
	
	
	
	
	
	
	
	
}
