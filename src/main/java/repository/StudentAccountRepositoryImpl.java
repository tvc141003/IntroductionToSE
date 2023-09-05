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


import model.Student;
import model.StudentAccount;
import utils.HibernateUtils;

public class StudentAccountRepositoryImpl implements StudentAccountRepository{

	private static CriteriaQuery<Student> cr ;
	private static Root<Student> root;
	private static Session session;
	private static CriteriaBuilder cb;
	private static SessionFactory factory = HibernateUtils.getSessionFactory();

	
	public StudentAccount findByUsernameId(String username_id) {
		session = factory.openSession(); //open session
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		cr.select(root).where(cb.equal(root.get("studentId"), username_id));
		
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		session.close(); //close session
		if (list.size() == 0 ) 
			return null;
		
		
		return list.get(0).getAccount();
	}
	
	public boolean isExist(String username_id) {
		
		StudentAccount account = this.findByUsernameId(username_id);
		if (account == null) return false;
		
		return true;
		
	}

	public boolean isCorrect(String username_id, String password) {
		if (this.isExist(username_id) == false) {
			return false;
		}
		session = factory.openSession(); //open session
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		cr.select(root).where(cb.equal(root.get("studentId"), username_id));
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		
		session.close(); 
		
		if (list.get(0).getAccount().getPassword().equals(password)) {
			session.close();
			return true;
		}
		return false;
	}

	public void changePassword(StudentAccount origin, String newPasswrod) {
		
		if (this.isExist(origin.getStudent().getStudentId()) == false) {
			
			return;
		}
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		origin.setPassword(newPasswrod);
		CriteriaUpdate<StudentAccount> update = cb.createCriteriaUpdate(StudentAccount.class);
		Root<StudentAccount> managerRoot = update.from(StudentAccount.class);
		Transaction tx = session.beginTransaction();
		
		update.set("password", origin.getPassword());
		update.where(cb.equal(managerRoot.get("id"), origin.getId()));
		session.createQuery(update).executeUpdate();
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void save(StudentAccount origin) {
		session = factory.openSession(); //open session
		session.saveOrUpdate(origin);
		session.close();//close session
		
		
	}
	
	public void remove(String username_id) {
		
		StudentAccount account = this.findByUsernameId(username_id);
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


	
	
	
	
	private static  StudentAccountRepositoryImpl INSTANCE ;
	private StudentAccountRepositoryImpl() {
		
	}
	
	
	

	public static StudentAccountRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new StudentAccountRepositoryImpl();
			
		}
		return INSTANCE;
	}

}
