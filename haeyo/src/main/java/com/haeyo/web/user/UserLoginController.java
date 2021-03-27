package com.haeyo.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.haeyo.biz.user.UserVO;
import com.haeyo.biz.user.impl.UserServiceImpl;

@Controller
//@SessionAttributes("user")
public class UserLoginController {
	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	
	@Autowired
	public  UserServiceImpl userServiceImpl;
	
	
//	@PostMapping("/insertUser.do")	// 브라우저의 url과 mapping 시켜주는 역할
//	public String insertUser(@ModelAttribute UserVO vo) {
//	//public String insertUser() {
//		System.out.println(vo);
//		System.out.println("insertUser");
//		userService.insertUser(vo);
//		System.out.println("인서트유저두 컨트롤러");
//		return "Login";
//	}
	
	@PostMapping("/insertUser.do")
	public String insertUser(@ModelAttribute UserVO vo) throws Exception {
		logger.info("UserVO : "+vo);
		userServiceImpl.insertUser(vo);	// 
		return "Login";
	}
	
//	@PostMapping("/login.do")
//	public String login(UserVO vo, Model model) {
//		System.out.println("로그인 처리");
//		model.addAttribute("user", userService.getUser(vo));
//		System.out.println(model);
//		return "index";
//	}
	
	
	// 로그인이 실행되는 컨트롤
	@PostMapping("/login.do")
	public String login(UserVO vo, Model model) {
		logger.info("UserVO : "+vo);
		model.addAttribute("user", userServiceImpl.getUser(vo));
		return "index";
	}
}
