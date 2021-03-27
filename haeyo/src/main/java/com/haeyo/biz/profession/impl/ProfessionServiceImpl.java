package com.haeyo.biz.profession.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haeyo.biz.profession.ProfessionListVO;
import com.haeyo.biz.profession.ProfessionService;
import com.haeyo.biz.profession.ProfessionSubVO;

@Service("ProfessionService")
public class ProfessionServiceImpl implements ProfessionService {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private ProfessionDAO professionDAO;
	
	@Override
	public List<ProfessionListVO> getProList(ProfessionListVO vo) {
		System.out.println("전문가 추천 리스트 출력");
		return professionDAO.getProList(vo);
	}

	@Override
	public List<ProfessionListVO> getAllList(ProfessionListVO vo) {
		System.out.println("전문가 서비스 전체 리스트 출력");
		System.out.println(session.getAttribute("user"));
		return professionDAO.getAllList(vo);
	}

	@Override
	public ProfessionListVO getDetail(ProfessionListVO vo) {
		System.out.println("전문가 디테일 페이지");
		return professionDAO.getDetail(vo);
	}

	@Override
	public List<ProfessionListVO> getReview(ProfessionListVO vo) {
		System.out.println("전문가 리뷰 페이지");
		return professionDAO.getReview(vo);
	}

	@Override
	public ProfessionSubVO getSubCate(ProfessionListVO vo) {
		System.out.println("서브카테고리 가져오기");
		if(vo.getpCategory().equals("수리")) {
			System.out.println("수리");
//			ProfessionSubVO proRepair = professionDAO.RepairCate(vo);
			return professionDAO.RepairCate(vo);
		}else if(vo.getpCategory().equals("이사")) {
			return professionDAO.MovingCate(vo);
		}
		return professionDAO.CleaningCate(vo);
	}
	
	@Override
	public ProfessionSubVO getSubCateTest(Map<String, Object> map) {
		System.out.println("서브카테고리 가져오기");
		if(map.get("pCategory").equals("수리")) {
			System.out.println("수리");
//			ProfessionSubVO proRepair = professionDAO.RepairCate(vo);
			return professionDAO.RepairCateTest(map);
		}else if(map.get("pCategory").equals("이사")) {
			return professionDAO.MovingCateTest(map);
		}
		return professionDAO.CleaningCateTest(map);
	}
	
	@GetMapping("/test")
	@ResponseBody
	public String test(@RequestBody Map<String, Object> param) {
	String pNo = param.get("pNo").toString();
	return pNo;
	}


}
