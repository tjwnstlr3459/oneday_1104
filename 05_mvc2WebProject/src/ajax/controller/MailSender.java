package ajax.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	// 매개변수로 받은 이메일 주소로 랜덤코드 생성해서 메일을 전송한 후 랜덤코드를 리턴
	public String mailSend(String email) {
		Random r = new Random(); // 랜덤 코드 생성 용
		StringBuilder sb = new StringBuilder(); // 랜덤코드

		// 0~9 랜덤코드 6자리
		for (int i = 0; i < 6; i++) {
			sb.append(r.nextInt(10));
		}
		System.out.println("생성된 랜덤코드 : " + sb.toString());

		// smtp 세팅
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.prot", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// 전송용 session 만들기
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dragon2009t", "wlgma123@");
			}
		});
		
		MimeMessage msg = new MimeMessage(session);

		try {
			// 보내는 날짜
			msg.setSentDate(new Date());
			// 보내는 사람
			msg.setFrom(new InternetAddress("dragon2009t@gmail.com", "KH 당산 지점"));
			// 받는 사람
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 메일 제목
			msg.setSubject("KH 정보교육원 인증메일입니다.", "UTF-8");
			// 메일 내용
			msg.setContent("<h1>안녕하세요. KH정보교육원입니다.<h1>" + "<h3>인증번호는[" + sb.toString() + "] 입니다.",
					"text/html;charset=UTF-8");
			// 메일 전송
			Transport.send(msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}