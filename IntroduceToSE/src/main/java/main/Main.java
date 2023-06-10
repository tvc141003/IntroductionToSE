package main;




import javax.persistence.FetchType;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.Eager;
import utils.HibernateUtils;
import utils.MailUtils;




public class Main {
	
	public static void main(String args[]) {
		SessionFactory factory = HibernateUtils.buildSessionFactory();
		Session session = factory.openSession();
		session.setHibernateFlushMode(FlushMode.AUTO);
		Transaction tx = session.beginTransaction();

		MailUtils mailUtils = MailUtils.getInstance();
		mailUtils.sendPassword("lehuutrong141@gmail.com","Hello");
		

	
		
	


		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	
	
}}
