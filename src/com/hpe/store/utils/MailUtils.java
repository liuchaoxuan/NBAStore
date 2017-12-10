/**
 * 
 */
package com.hpe.store.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author kyrie liu
 * @date Nov 8, 2017
 * @time 11:05:03 PM
 * @version 1.0
 */
public class MailUtils {
	/**
	 * 发送邮件的方法
	 * @param to	:收件人
	 * @param code	:激活码
	 */
	public static void sendMail(String to,String code){
		/**
		 * 1.获得一个Session对象.
		 * 2.创建一个代表邮件的对象Message.
		 * 3.发送邮件Transport
		 */
		// 1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.yeah.net");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nbastore@yeah.net", "1Qaz2wsx3edc");
			}

		});
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		// 设置发件人:
		try {
			message.setFrom(new InternetAddress("nbastore@yeah.net"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC   密送BCC
			// 设置标题
			message.setSubject("Welcom to NBAStore, please active your account");
			// 设置邮件正文:
			message.setContent("<h1>Welcom to NBAStore! click to active your account!</h1><h3><a href='http://localhost:8080/NBAStore/user_active.action?code="+code+"'>http://nbastore/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
			System.out.print(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	//for test
	public static void main(String[] args) {
		String code = UUIDUtils.getUUID();
		sendMail("nbanets@yeah.net.com",code);
	}

}
