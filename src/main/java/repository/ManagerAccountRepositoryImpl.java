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

import model.Manager;
import model.ManagerAccount;

import utils.HibernateUtils;

public class ManagerAccountRepositoryImpl implements ManagerAccountRepository{
	
	SessionFactory factory = HibernateUtils.getSessionFactory();
	
	private static Root<Manager> root;
	private static Session session;
	private static CriteriaBuilder cb;
	

	
	
	
	@Override
	public ManagerAccount findByUsernameId(String username_id) {
		session = factory.openSession(); // close session
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Manager> cr = cb.createQuery(Manager.class);
		root  = cr.from(Manager.class);
		cr.select(root).where(cb.equal(root.get("managerId"), username_id));
		
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list  = query.getResultList();
		session.close(); // close session
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
		System.out.println(username_id + password);
		if (this.isExist(username_id) == false) 
			return false;
		
		session = factory.openSession(); //open session
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Manager> cr = cb.createQuery(Manager.class);
		root = cr.from(Manager.class);
		cr.select(root).where(cb.equal(root.get("managerId"), username_id));
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list  = query.getResultList();
		
		session.close(); // close session
		
		if (list.get(0).getAccount().getPassword().equals(password)) 
			return true;
		
		return false;
	}

	public void changePassword(ManagerAccount origin, String newPassword) {
		if (this.isExist(origin.getManager().getManagerId()) == false) return;
		
		session = factory.openSession(); // open session
		
		origin.setPassword(newPassword);
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
		} finally {
			session.close(); // close session
		}
	
	
	}

	public void save(ManagerAccount origin) {
		session = factory.openSession();
		session.save(origin);
		
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

	public void remove(String username_id) {
		ManagerAccount account = this.findByUsernameId(username_id);
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
	private ManagerAccountRepositoryImpl() {
		System.out.print("s");
	}
	
	private static  ManagerAccountRepositoryImpl INSTANCE;
	
	
	public static ManagerAccountRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManagerAccountRepositoryImpl();
		}
		return INSTANCE;
	}

	
	

}
