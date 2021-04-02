package com.haeyo.biz.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haeyo.biz.user.UserVO;

@Repository
public class UserDAOMybatis {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOMybatis.class);
	
	//장현아
	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertUser(UserVO vo) {
		System.out.println("Mybatis로 insertUser() 기능처리");
		mybatis.insert("UserDAO.insertUser", vo);
	}
	
	public UserVO getUser(UserVO vo) {
		System.out.println("Mybatis로 getUser() 기능처리");
		return (UserVO)mybatis.selectOne("UserDAO.getUser", vo);
	}
	
	//손예인
	public void insertUserPic(UserVO vo) {
		System.out.println("Mybatis로 insertUserPic() 기능 처리! 얏호!");
		mybatis.insert("UserDAO.insert",vo);
	}
	
	public UserVO selectOneUser(UserVO vo) {
		System.out.println("Mybatis로 selectUser() 기능 처리! 얏호!");
		return (UserVO)mybatis.selectOne("UserMypageDAO.selectOneUser", vo);
	}
	
	public void selectPwdUser(UserVO vo) {
		System.out.println("UserDAOMybatis 셀렉트 패스워드 유저 기능 처리! 유후 ");
		mybatis.selectOne("UserDAO.select", vo);
	}
	
	public void deleteoneUser(int uNo) {
		logger.info("UserVO uNo : " + uNo);
		System.out.println("UserDAOMybatis 딜리트 유저 기능처리");
		mybatis.delete("UserMypageDAO.deleteoneUser", uNo);
	}
	
	public void updateMypage(UserVO vo) {
		logger.info("UserDAOMybatis updateMypage UserVO vo :" + vo);
		System.out.println("UserDAOMybatis updateMypage 넘어왓다 야호 ");
		mybatis.update("UserMypageDAO.updateMypage", vo);
	}
	
	
}
