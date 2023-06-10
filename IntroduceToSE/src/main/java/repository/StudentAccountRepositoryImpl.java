package repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
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
import utils.HibernateUtils;

public class StudentAccountRepositoryImpl implements StudentAccountRepository{

	private static CriteriaQuery<Student> cr ;
	private static Root<Student> root;
	private static Session session;
	private static CriteriaBuilder cb;
	private static SessionFactory factory = HibernateUtils.getSessionFactory();

	
	public boolean isExist(String username_id) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		cr.select(root).where(cb.equal(root.get("studentId"), username_id));
		
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		
		if (list.size() == 0 ) 
			return false;
		
		return true ;
	}

	public boolean isCorrect(String username_id, String password) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		if (this.isExist(username_id) == false) {
			session.close();
			return false;
		}
		
		
		cr.select(root).where(cb.equal(root.get("studentId"), username_id));
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		
		if (list.get(0).getAccount().getPassword().equals(password)) {
			session.close();
			return true;
		}
		
		session.close();
		return false;
	}

	public void changePassword(StudentAccount origin, String newPasswrod) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		if (this.isExist(origin.getStudent().getStudentId()) == false) {
			session.close();
			return;
		}
		
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
		// TODO Auto-generated method stub
		
	}

	public void remove(StudentAccount origin) {
		// TODO Auto-generated method stub
		
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
