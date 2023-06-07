package repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Teacher;

import utils.HibernateUtils;


public class TeacherRepositoryImpl implements TeacherRepository {
	
	private static CriteriaQuery<Teacher> cr ;
	private static Root<Teacher> root;
	private static Session session;
	private static CriteriaBuilder cb;
	
	static {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Teacher.class);
		root  = cr.from(Teacher.class);
		
	}
	
	public List<Teacher> findAll() {
		
		cr.select(root) ;
		org.hibernate.query.Query<Teacher> query = session.createQuery(cr);
		List<Teacher> list  = query.getResultList();
		if (list.size() == 0 ) 
			return null;
		return list;
		
	}

	public Teacher findById(String id) {
		cr.select(root).where(cb.equal(root.get("teacherId"), id));
		org.hibernate.query.Query<Teacher> query = session.createQuery(cr);
		List<Teacher> list  = query.getResultList();
		if (list.size() == 0 ) 
			return null;
		Teacher teacher = list.get(0);
		return teacher;
	}

	public void save( Teacher model) {
		session.save(model);
		
		
	}

	public void remove(String id) {
		CriteriaDelete<Teacher> cd = cb.createCriteriaDelete(Teacher.class);
		
		cd.where(cb.equal(root.get("teacherId"),id));
		
		session.createQuery(cd).executeUpdate();
		
		
	}

	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Teacher findByName(String name) {
		Predicate inFirstName = cb.like(((Expression)root.get("first_name")),"%" + name +"%");
		Predicate inLastName = cb.like(((Expression)root.get("last_name")),"%" + name +"%") ;
		
		cr.select(root).where(cb.or(inFirstName,inLastName));
		
		org.hibernate.query.Query<Teacher> query = session.createQuery(cr);
		List<Teacher> list  = query.getResultList();
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
