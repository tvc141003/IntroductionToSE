package main;




import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import utils.HibernateUtils;
import utils.MailUtils;

public class Main {
	
	public static void main(String args[]) {
		SessionFactory factory = HibernateUtils.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		
		MailUtils mailUtils = MailUtils.getInstance();
		mailUtils.sendEmail("anhchipro258@gmail.com", "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <style >\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, Helvetica, sans-serif; \r\n"
				+ "            width: 500px;\r\n"
				+ "            border: 2px solid rgb(235, 227, 227);\r\n"
				+ "        }\r\n"
				+ "        .header{\r\n"
				+ "            color: yellow;\r\n"
				+ "            background-color: black;\r\n"
				+ "            height: 100px;\r\n"
				+ "            justify-content: center;\r\n"
				+ "            text-align: center;\r\n"
				+ "            margin: 0 auto;\r\n"
				+ "            display: flex;\r\n"
				+ "            align-items: center;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .header_content{\r\n"
				+ "            justify-content: center;\r\n"
				+ "            margin: auto;\r\n"
				+ "            display: flex;\r\n"
				+ "            align-items: center;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .container {\r\n"
				+ "            padding: 0 20px 0 20px;\r\n"
				+ "            display: block;\r\n"
				+ "            margin: 20px 0;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .title{\r\n"
				+ "            margin: 0;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .description {\r\n"
				+ "            margin: 20px 0 0 0;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .password {\r\n"
				+ "            padding: 0 0 0 10px;\r\n"
				+ "            color: rgb(223, 223, 20);\r\n"
				+ "            font-size: 30px;\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            margin: 10px 0;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"header\">\r\n"
				+ "        <h1 class=\"header_content\">Clone Portal Group 108</h1>\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"container\">\r\n"
				+ "        <h2 class=\"title\">Reset password</h2>\r\n"
				+ "        <p class=\"description\">Your password</p>\r\n"
				+ "        <p class=\"password\">Password is:</p>\r\n"
				+ "        <p class=\"auto_text\">This is an automated message, please do not reply. Thanks</p>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>");
		
		
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	
	
}}
