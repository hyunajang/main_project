package com.haeyo.web.user;

import javax.servlet.http.HttpSession;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.haeyo.biz.user.impl.UserServiceImpl;

@Controller
public class UserLogoutController {
//	private static final Logger logger = LoggerFactory.getLogger(UserLogoutController.class);

	@Autowired
	public UserServiceImpl userServiceImpl;

	@PostMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 처리");
		session.invalidate();
		return "Login";
	}
}
