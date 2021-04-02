package com.haeyo.web.professionMypage;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.haeyo.biz.profession.ProfessionVO;
import com.haeyo.biz.professionMypage.impl.ProfessionMypageService;
import com.haeyo.biz.user.UserVO;

@Controller
//@SessionAttributes("profession")
public class ProfessionMypageController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfessionMypageController.class);
	
	@Autowired
	private ProfessionMypageService professionMypageService;
		
	@RequestMapping(value="/updateProfile.do", method=RequestMethod.GET)
	public String updateProfileBefore(HttpSession session, Model model) {
//		ProfessionVO professionVO = new ProfessionVO();
//		professionVO = (ProfessionVO) session.getAttribute("profession");
//		model.addAttribute("profession", professionVO);
//		logger.info("profession : " + professionVO);
		return "proMypageUpdate";
	}
	
	@RequestMapping(value="/updateProfile.do", method=RequestMethod.POST)
	public String updateProfile(@ModelAttribute ProfessionVO vo, MultipartHttpServletRequest request) throws Exception {
//		professionMypageService.updateUpload(request);
		logger.info("upload+professionVO : " + vo);
		professionMypageService.updateProfile(vo, request);
		return "redirect: getProfile.do";
	}
	
	@PostMapping("/deleteProfile.do")
	public String deleteProfile(@RequestParam("pNo") int pNo) {
		logger.info("delete_info :" + pNo);
		professionMypageService.deleteProfile(pNo);
		return "index"; //로그인 처리가 된 상태로  main으로 들어가게...?
	}
	
	@GetMapping("/getProfile.do")
	public String getProfile(HttpSession session, Model model) {
		UserVO userVO = (UserVO)session.getAttribute("user");
		model.addAttribute("professionProfile", professionMypageService.getProfile(userVO));
		logger.info("professionProfile : " + professionMypageService.getProfile(userVO));
		session.setAttribute("profession", professionMypageService.getProfile(userVO));
		model.addAttribute("user", userVO);
		logger.info("session : " + session.getAttribute("profession"));
		return "proMypageProfile";
	}

}
