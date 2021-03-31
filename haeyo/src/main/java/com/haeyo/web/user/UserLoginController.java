package com.haeyo.web.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haeyo.biz.user.UserVO;
import com.haeyo.biz.user.impl.UserServiceImpl;

@Controller
//@SessionAttributes("user")
public class UserLoginController {
	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

	@Autowired
	public UserServiceImpl userServiceImpl;

	@PostMapping("/insertUser.do")
	public String insertUser(@ModelAttribute UserVO vo) throws Exception {
		logger.info("UserVO : " + vo);
		userServiceImpl.insertUser(vo); //
		return "Login";
	}

	// 로그인이 실행되는 컨트롤
//	@PostMapping("/login.do")
//	public String login(UserVO vo, Model model) {
//		logger.info("UserVO : "+vo);
//		
//		
//	if(vo == null) {
//		int result = 0;
//		model.addAttribute("result", result);
//		return "redirect:/Login";
//	}
//		model.addAttribute("user", userServiceImpl.getUser(vo));
//		return "redirect:/index";

	// 로그인이 실행되는 컨트롤
	@PostMapping("/login.do")
	public String login(UserVO vo, Model model) {
		logger.info("UserVO : " + vo);
		model.addAttribute("user", userServiceImpl.getUser(vo));
		return "index";
	}
}
