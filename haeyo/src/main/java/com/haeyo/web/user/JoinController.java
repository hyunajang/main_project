package com.haeyo.web.user;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@SessionAttributes("user")
public class JoinController {

	@Autowired
	MailSender mailSender;
	 
	@GetMapping(value = "/mailCheck", consumes = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody
	public String mailCheck(String email) throws Exception {
//		log.info("이메일 데이터 전송확인");
//		log.info("인증 메일 : " + email);

		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111; // 111111 - 999999
//		log.info("인증번호 : " + checkNum);

		// 이메일 보내기
		String setFrom = "jisung1367@gmail.com";
		String toEmail = email;
		String title = "독거노인 회원가입 인증 이메일 입니다.";
		String content = "독거노인에 가입해주셔서 감사합니다." + "<br/><br/>" + "인증 번호는 " + checkNum + " 입니다.<br/>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		try {
			MimeMessage message = MailSendException.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toEmail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String num = Integer.toString(checkNum);
		return num;
	}
}