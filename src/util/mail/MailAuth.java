package util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator {
	
	PasswordAuthentication pa;
	
	public MailAuth() {
		String mail_id = "gonggongyeonhee";
		String mail_pw = "gong123!!!";
		
		pa = new PasswordAuthentication(mail_id, mail_pw);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
	
	
}
