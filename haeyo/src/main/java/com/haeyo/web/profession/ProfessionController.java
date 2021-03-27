package com.haeyo.web.profession;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.haeyo.biz.profession.ProfessionListVO;
import com.haeyo.biz.profession.ProfessionService;
import com.haeyo.biz.profession.ProfessionSubVO;

@Controller
public class ProfessionController {
	
	@Autowired
	private ProfessionService professionService;
	
	@GetMapping("/recommend.do")
	public String getProList(ProfessionListVO vo, Model model) {
		System.out.println("전문가 컨트롤러 처리");
		model.addAttribute("proRecom", professionService.getProList(vo));
		model.addAttribute("proList", professionService.getAllList(vo));
		System.out.println(model);
		return "profession";
	}
	
	@GetMapping("/detail.do")
	public String getDetail(ProfessionListVO vo, Model model) {
		System.out.println("전문가 디테일 컨트롤러");
		model.addAttribute("proDetail", professionService.getDetail(vo));
		model.addAttribute("proReview", professionService.getReview(vo));
		model.addAttribute("proSubCate", professionService.getSubCate(vo));
//		System.out.println(map);
		return "professionDetail";
	}
	
	@GetMapping("/application.do")
	public String application(textTest vo, Model model, HttpSession session) {
		System.out.println("application.do");
		model.addAttribute("subCategory", vo);
		return "application";
	}

}
