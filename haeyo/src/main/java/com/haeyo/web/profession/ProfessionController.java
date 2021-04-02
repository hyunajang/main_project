package com.haeyo.web.profession;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.haeyo.biz.profession.ProfessionBookmarksVO;
import com.haeyo.biz.profession.ProfessionListVO;
import com.haeyo.biz.profession.ProfessionService;
import com.haeyo.biz.profession.ProfessionVO;
import com.haeyo.biz.reservation.ReservationVO;

@Controller
public class ProfessionController {
	private static final Logger logger = LoggerFactory.getLogger(ProfessionController.class);

	@Autowired
	private ProfessionService professionService;

	@GetMapping("/recommend.do")
	public String getProList(ProfessionListVO vo, Model model) {
		System.out.println("전문가 컨트롤러 처리");
		String serchloc = vo.getSerchloc();
		model.addAttribute("serchloc", serchloc);
		logger.info("serchloc" + serchloc);
		model.addAttribute("proRecom", professionService.getProList(vo));
		logger.info("professionService.getProList(vo)" + professionService.getProList(vo));
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
		logger.info("professionService.getSubCate(vo)" + professionService.getSubCate(vo));
		return "professionDetail";
	}

	/*
	 * //ajax전문가 리스트 출력
	 * 
	 * @RequestMapping(value="/getList.do", method=RequestMethod.GET,
	 * produces="application/text;charset=utf-8")
	 * 
	 * @ResponseBody public String getList(@ModelAttribute ProfessionListVO vo,
	 * Model model) throws JsonProcessingException { model.addAttribute("proList",
	 * professionService.getList(vo)); logger.info("getList : " + vo);
	 * logger.info("getAllList" + professionService.getList(vo)); ObjectMapper
	 * mapper = new ObjectMapper(); String jsonString =
	 * mapper.writeValueAsString(professionService.getList(vo));
	 * logger.info("jsonString : " + jsonString); return jsonString; }
	 */

	// ajax전문가 리스트 출력 왜 안되는가??? 물어봐야지

	@RequestMapping(value = "/getList.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<ProfessionListVO> getList(@ModelAttribute ProfessionListVO vo) {
		logger.info("getAllList" + professionService.getList(vo));
		List<ProfessionListVO> proList = professionService.getList(vo);
		logger.info("proList" + proList);
		
		return proList;
	}

	// 북마크
	@RequestMapping(value = "/bookmark.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public int bookmark(@ModelAttribute ProfessionBookmarksVO vo, HttpSession session) {
		logger.info("bookmark vo : " + vo);
		return professionService.checkBook(vo, session);
	}

	// 전문가일정 가져오기
	@RequestMapping(value = "/calendal.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<ReservationVO> calendarView(@ModelAttribute ProfessionVO vo, Model model) throws JsonProcessingException {
		logger.info("캘린더 리스트 : " + vo);
		logger.info("캘린더 반환 : " + professionService.getproReservation(vo));
		return professionService.getproReservation(vo);
	}	

	// 전문가 달력 페이지 연결
	@GetMapping("/viewproCalendar.do")
	public String viewproCalendar(@ModelAttribute ProfessionVO vo, Model model) {
		model.addAttribute("pNo", vo);
		logger.info("viewPorCalendar : " + vo);
		return "calendal";
	}
}
