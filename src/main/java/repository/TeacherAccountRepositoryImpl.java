package repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import model.Teacher;
import model.TeacherAccount;
import utils.HibernateUtils;

public class TeacherAccountRepositoryImpl implements TeacherAccountRepository {

SessionFactory factory = HibernateUtils.getSessionFactory();
	
	private static Root<Teacher> root;
	private static Session session;
	private static CriteriaBuilder cb;

	public TeacherAccount findByUsernameId(String username_id) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Teacher> cr = cb.createQuery(Teacher.class);
		root  = cr.from(Teacher.class);
		cr.select(root).where(cb.equal(root.get("teacherId"), username_id));
		
		Query<Teacher> query = session.createQuery(cr);
		List<Teacher> list  = query.getResultList();
		session.close();
		if (list.size() == 0 ) 
		{
			
			return null;
		}
		
		return list.get(0).getAccount();
	}

	public boolean isExist(String username_id) {
		if (this.findByUsernameId(username_id) == null ) 
		{
			
			return false;
		}
		
		return true ;
	}

	public boolean isCorrect(String username_id, String password) {
		if (this.isExist(username_id) == false) 
			return false;
		
		session = factory.openSession(); //open session
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Teacher> cr = cb.createQuery(Teacher.class);
		root = cr.from(Teacher.class);
		cr.select(root).where(cb.equal(root.get("teacherId"), username_id));
		org.hibernate.query.Query<Teacher> query = session.createQuery(cr);
		List<Teacher> list  = query.getResultList();
		
		session.close(); // close session
		
		if (list.get(0).getAccount().getPassword().equals(password)) 
			return true;
		
		return false;
	}

	public void changePassword(TeacherAccount origin, String newPassword) {
if (this.isExist(origin.getTeacher().getTeacherId()) == false) return;
		
		session = factory.openSession(); // open session
		
		origin.setPassword(newPassword);
		CriteriaUpdate<TeacherAccount> update = cb.createCriteriaUpdate(TeacherAccount.class);
		Root<TeacherAccount> managerRoot = update.from(TeacherAccount.class);
		update.set("password", origin.getPassword());
		
		
		
		Transaction tx = session.beginTransaction();
		update.where(cb.equal(managerRoot.get("id"), origin.getId()));
		session.createQuery(update).executeUpdate();
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

	public void save(TeacherAccount origin) {
		session = factory.openSession();
		session.saveOrUpdate(origin);
		
		Transaction tx = session.beginTransaction();
		
		
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();  // close session
		}
		
	}

	@Override
	public void remove(String username_id) {
		TeacherAccount account = this.findByUsernameId(username_id);
		if (account == null) return ;
		
		
		session = factory.openSession(); //open session
		Transaction tx = session.beginTransaction();
		session.delete(account);
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();  // close session
		}
		
	}

	private static  TeacherAccountRepositoryImpl INSTANCE ;
	private TeacherAccountRepositoryImpl() {
		
	}
	
	
	public static TeacherAccountRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TeacherAccountRepositoryImpl();
			
		}
		return INSTANCE;
	}
	

}
