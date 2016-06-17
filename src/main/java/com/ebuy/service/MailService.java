package com.ebuy.service;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 
public class MailService {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;


 
	public static boolean sendEmail(String sendTo , String body , String subject) throws AddressException, MessagingException {
 
		try {
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");

			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
			//generateMailMessage.addRecipient(Message.RecipientType., new InternetAddress("ahmed_amin007@hotmail.com"));
			generateMailMessage.setSubject("ebuy System" + subject);
			String emailBody = "Welcome to ebuy System " + "<br><br> " +  body + " <br>ebuy Team";
			generateMailMessage.setContent(emailBody, "text/html");
	 
			Transport transport = getMailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", "ecafeweb@gmail.com", "ec@fe123");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
			return true;
		}
		catch (Exception ex) {
			return false;
		}
		
	}
}