package com.haeyo.web.user;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

import com.haeyo.biz.board.impl.TogetherBoardServiceImpl;
import com.haeyo.biz.board.impl.TogetherBoardVO;
import com.haeyo.biz.user.UserService;
import com.haeyo.biz.user.UserVO;
import com.haeyo.biz.user.impl.UserDAOMybatis;
import com.haeyo.biz.user.impl.UserServiceImpl;
import com.haeyo.web.board.impl.TogetherBoardController;

@Controller
@SessionAttributes("user")		// 세션에 user객체를 보관해라
public class UserMypageController {
	private static final Logger logger = LoggerFactory.getLogger(UserMypageController.class);
	
	@Autowired
	public UserServiceImpl userServiceImpl;

	@Autowired
	HttpSession session;
	
	@PostMapping("/uploadUserPic.do")
	public String insertBoard(@ModelAttribute UserVO vo) throws Exception {
		logger.info("파라미터:"+vo);
		vo = userServiceImpl.uploadUserPic(vo);
		logger.info("파라미터:"+vo);
		userServiceImpl.insertUser(vo);
		return "redirect: mypageinfo.do";
	}


	
	//마이페이지 수정화면 보여주기 
	@GetMapping("/UserMypageInfoUpdate.do")
	public String MypageUpdate(Model model, HttpSession session) {
		
		return "UserMypageInfoUpdate";
	}
		
	@PostMapping("/UserMypageInfoUpdate.do")
	//@RequestMapping("/UserMypageInfoUpdate.do")
	// @ModelAttribute("user")를 해주면 vo에 session에 있는 user를 채워넣고,
	// 브라우저가 보낸 수정된 부분만 갱신한다
	public String MypageUpdate(@ModelAttribute("user") UserVO vo, Model model) throws Exception {
		System.out.println("UserMypageController MypageUpdate 냥냥 ");
		System.out.println("MypageUpdate " + vo);
		logger.info("MypageUpdate  controller" + vo);
		//logger.info("session" + session.getAttribute("user"));
		//vo = (UserVO)session.getAttribute("user");
//		
		userServiceImpl.uploadUserPic(vo);
		userServiceImpl.updateMypage(vo);
		
		vo.setFilePath("resources/image/" + vo.getUploadFile().getOriginalFilename());
		model.addAttribute("user", vo);		// 세션에 다시 user가 저장된다
		
		return "UserMypage";
	}
//	
//	@RequestMapping("/UserMypageInfoUpdate.do")
//	public String updateMypage(UserVO vo) {
//		userServiceImpl.updateMypage(vo);
//		return "mypageinfo.do";
//	}
	
	
		
	// 마이페이지 인포화면 뷰
	@GetMapping("/mypageinfo.do")
	public String selectOneUser(Model model, HttpSession session) {
		logger.info("GetMapping__session" + session.getAttribute("user"));
		UserVO user = (UserVO)session.getAttribute("user");
		System.out.println("마이페이지 인포 화면에 회원 데이터 출력");
		// model에 user로 저장하면 user이름이 session에 저장되도록 했으므로 세션에 보관됨
		model.addAttribute("user", userServiceImpl.selectOneUser(user));
		logger.info("user result" + userServiceImpl.selectOneUser(user));

		//session.setAttribute("user", userServiceImpl.selectOneUser(user));
		//return "redirect: mypageinfo.do";		
		return "UserMypage";
	}

	@GetMapping("/UserMypagePwd.do")	//비밀번호니까 post가 맞나요?
	public String selectPwdUser(Model model, HttpSession session) {
		logger.info("session" + session.getAttribute("user"));
		UserVO user = (UserVO)session.getAttribute("user");
		String uPwd = user.getuPwd();
		logger.info("마이페이지 비밀번호 출력  uPwd" + uPwd);
		return "UserMypagePwd";
	}
	
	@RequestMapping(value="/deleteUser.do", method=RequestMethod.GET)
	public String deleteoneUser(HttpSession session, @RequestParam("uNo") int uNo) {
		userServiceImpl.deleteoneUser(uNo);
		logger.info("UserMypageController 회원 탈최 !  uNo" + uNo);
		return "index";
	}


			
}