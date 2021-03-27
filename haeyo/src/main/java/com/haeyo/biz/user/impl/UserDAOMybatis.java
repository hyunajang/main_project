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
}
