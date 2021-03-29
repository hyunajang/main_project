package com.haeyo.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	@Autowired
	private JavaMailSender mailSender;

}
