package com.haeyo.biz.user.impl;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haeyo.biz.user.UserVO;

@Service("UserService")
public class UserServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAOMybatis userDAO;
	
	@Autowired
	HttpSession session;
	
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
	}

	public UserVO getUser(UserVO vo) {
		logger.info("UserVO"+vo);
		return userDAO.getUser(vo);
	}
}
