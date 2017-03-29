package ua.com.myshop.service;

public interface MailSendingService {

	void sendMail(String content, String email, String mailBody);
}
