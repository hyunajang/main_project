package com.haeyo.biz.user;

public interface UserService {
	//장현아 210323
	void insertUser(UserVO vo);
	
	UserVO getUser(UserVO vo);
	
	//손예인
	UserVO selectOneUser(UserVO vo);
	
	void selectPwdUser(UserVO vo);
	
	void deleteoneUser(int uNo);
	
	void updateMypage(UserVO vo);	//유저 마이페이지 정보 수정update
}
