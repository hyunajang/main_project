package com.haeyo.biz.user;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public interface UserService {
	//장현아 210323
	void insertUser(UserVO vo);
	
	UserVO getUser(UserVO vo);
}