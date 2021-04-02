package com.haeyo.biz.reservation;


public interface ReservationService {
	//예약정보 넣기
	void insertReservaiton(ReservationVO vo);
	
	String application(ReservationCategoryVO vo);
	
}
