package com.haeyo.biz.user.impl;


import java.io.File;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.haeyo.biz.user.UserService;
import com.haeyo.biz.user.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAOMybatis userDAO;
	
	@Autowired
	HttpSession session;
	
	//장현아
	/* 회원가입 */
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
	}

	/* 로그인 */
	public UserVO getUser(UserVO vo) {
		logger.info("UserVO"+vo);
		return userDAO.getUser(vo);
	}
	
	//손예인
	//@RequestMapping("/uploadUserPic.do")
	public UserVO uploadUserPic(UserVO vo) throws Exception {
		MultipartFile uploadFile = vo.getUploadFile(); //파일 업로드 객체 생성

		System.out.println("uploadBoard() 처리 중  uploadFile "+ uploadFile);

		if(!uploadFile.isEmpty()) {
			String uPic = uploadFile.getOriginalFilename();
			String rootPath = session.getServletContext().getRealPath("/");
			String attachPath ="/resources/image/";
			System.out.println(rootPath+attachPath+uPic);
			uploadFile.transferTo(new File(rootPath+attachPath+uPic)); //해당 경로에 받은 이름의 파일을 만들어 저장
			vo.setuPic(uPic);
			userDAO.updateMypage(vo);
		}
		return vo;
	}
	
	@Override
	public UserVO selectOneUser(UserVO vo) {
		System.out.println("셀렉트 원 유저 vo 마이페이지로 가자~!");
		return (UserVO)userDAO.selectOneUser(vo);
	}
	
	@Override
	public void selectPwdUser(UserVO vo) {
		System.out.println("셀렉트 유저 패스워드 vo 마이페이지로 가자~!");
		userDAO.selectPwdUser(vo);

	}

	@Override
	public void deleteoneUser(int uNo) {
		System.out.println("유저서비스임플 딜리트원유저 가자~!");
		userDAO.deleteoneUser(uNo);

	}

	@Override
	public void updateMypage(UserVO vo) {
		userDAO.updateMypage(vo);
	}


}