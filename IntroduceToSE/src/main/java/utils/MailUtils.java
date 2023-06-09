package utils;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





public class MailUtils {
	private static  MailUtils INSTANCE ;
	Properties props ;
	

	
	
	private MailUtils() {
	//initial property
		
		props= new Properties();
		
		
		props.put("mail.smtp.host",MailConfig.HOST_NAME); // smtp host
		props.put("mail.smtp.port","587");
		props.put("mail.smtp.auth","true");
		
		props.put("mail.smtp.starttls.enable", "true");
		
	}
	
	public void sendEmail(String receiveEmail, String text) {
		//create session (javax.mail)
		
		Session session = Session.getInstance(props, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
		    }
		});	
	
		 try {
			MimeMessage message = new MimeMessage(session);
			// nguoi gui
			 message.setFrom(new InternetAddress(MailConfig.APP_EMAIL));
			 // nguoi nhan
			 message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveEmail, false));
			 // set tieu de 
			 message.setSubject("Reset password group 108");
			 //
			 message.setContent( text,"text/html");
		
			 	
			
			 
			 Transport.send(message);
			 
	            System.out.println("Message sent successfully");
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}
	public static MailUtils getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MailUtils();
			
		}
		return INSTANCE;
	}
	
	
	
}
