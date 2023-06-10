package repository;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Manager;
import model.ManagerAccount;
import model.Student;
import utils.HibernateUtils;

public class ManagerAccountRepositoryImpl implements ManagerAccountRepository{

	private static CriteriaQuery<Manager> cr ;
	private static Root<Manager> root;
	private static Session session;
	private static CriteriaBuilder cb;
	
	static {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Manager.class);
		root  = cr.from(Manager.class);
		
	}
	
	public boolean isExist(String username_id) {
		cr.select(root).where(cb.equal(root.get("managerId"), username_id));
		
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list  = query.getResultList();
		
		if (list.size() == 0 ) 
			return false;
		
		return true ;
	}

	public boolean isCorrect(String username_id, String password) {
		if (this.isExist(username_id) == false) return false;
		
		
		cr.select(root).where(cb.equal(root.get("managerId"), username_id));
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list  = query.getResultList();
		
		if (list.get(0).getAccount().getPassword().equals(password)) 
			return true;
		
		return false;
	}

	public void changePassword(ManagerAccount origin, String newPasswrod) {
		if (this.isExist(origin.getManager().getManagerId()) == false) return;
		
		origin.setPassword(newPasswrod);
		CriteriaUpdate<ManagerAccount> update = cb.createCriteriaUpdate(ManagerAccount.class);
		Root<ManagerAccount> managerRoot = update.from(ManagerAccount.class);
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
		}
	
	
	}

	public void save(ManagerAccount origin) {
		session.save(origin);
		
	}

	public void remove(ManagerAccount origin) {
		if (this.isExist(origin.getManager().getManagerId()) == false) return;
		
		CriteriaDelete<ManagerAccount> delete = cb.createCriteriaDelete(ManagerAccount.class);
		Root<ManagerAccount> managerRoot = delete.from(ManagerAccount.class);
		Transaction tx = session.beginTransaction();
		delete.where(cb.equal(managerRoot.get("id"), origin.getId()));
		session.createQuery(delete).executeUpdate();
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		
	}
	
	private static  ManagerAccountRepositoryImpl INSTANCE ;
	private ManagerAccountRepositoryImpl() {
		
	}
	
	
	

	public static ManagerAccountRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManagerAccountRepositoryImpl();
			
		}
		return INSTANCE;
	}
	

}
