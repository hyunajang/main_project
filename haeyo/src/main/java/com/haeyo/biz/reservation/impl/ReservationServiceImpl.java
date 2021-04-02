package com.haeyo.biz.reservation.impl;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haeyo.biz.reservation.ReservationCategoryVO;
import com.haeyo.biz.reservation.ReservationService;
import com.haeyo.biz.reservation.ReservationVO;

@Service("ReservationServiceImpl")
public class ReservationServiceImpl implements ReservationService {

	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	HttpSession session;

	@Autowired
	ReservationDAO reservationDAO;

	// 예약 인서트
	@Override
	public void insertReservaiton(ReservationVO vo) {
		logger.info("ReservationVO" + vo);
		reservationDAO.insertReservation(vo);
		if (vo.getRsvCategory().equals("수리")) {
			reservationDAO.insertRepair(vo);
		}else if (vo.getRsvCategory().equals("이사")) {
			reservationDAO.insertMoving(vo);
		}else if (vo.getRsvCategory().equals("청소")){
			reservationDAO.insertCleaning(vo);
		}
	}

	// 신청서 출력
	@Override
	public String application(ReservationCategoryVO vo) {
		logger.info("ReservationCategoryVO" + vo);
		if (session.getAttribute("user") != null)
			return "application";
		else
			return "Login";
	}

}
