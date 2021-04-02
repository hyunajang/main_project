package com.haeyo.biz.professionMypage.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haeyo.biz.profession.ProfessionVO;
import com.haeyo.biz.user.UserVO;

@Repository
public class ProfessionMypageDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void updateProfile(ProfessionVO vo) {
		System.out.println("mybatis로 updateProfile() 기능 처리");
		mybatis.update("professionMypageDAO.updateProfile", vo);
	}
	
	public void deleteProfile(int pNo) {
		System.out.println("mybatis로 deleteProfile() 기능 처리");
		mybatis.delete("professionMypageDAO.deleteProfile", pNo);
	}
	
	public ProfessionVO getProfile(UserVO user) {
		System.out.println("mybatis로 getProfile() 기능 처리");
		System.out.println("userVO : " + user + "확인");
		System.out.println((ProfessionVO) mybatis.selectOne("professionMypageDAO.getProfile", user));
		return (ProfessionVO) mybatis.selectOne("professionMypageDAO.getProfile", user);
		
	}
	

}
