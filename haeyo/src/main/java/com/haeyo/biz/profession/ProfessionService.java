package com.haeyo.biz.profession;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.haeyo.biz.reservation.ReservationVO;

public interface ProfessionService {
	List<ProfessionListVO> getProList(ProfessionListVO vo);
	List<ProfessionListVO> getAllList(ProfessionListVO vo);
	ProfessionListVO getDetail(ProfessionListVO vo);
	List<ProfessionListVO> getReview(ProfessionListVO vo);
	
	//카테고리 별 소카테고리 분류
	ProfessionSubVO getSubCate(ProfessionListVO vo);
	
	//전문가 리스트 select 출력
	List<ProfessionListVO> getList(ProfessionListVO vo);
	
	//북마크 인서트
	int checkBook(ProfessionBookmarksVO vo, HttpSession session);
	
	//예약 일정 셀렉트
	List<ReservationVO> getproReservation(ProfessionVO vo);
}	
