package com.haeyo.biz.profession.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haeyo.biz.profession.ProfessionListVO;
import com.haeyo.biz.profession.ProfessionSubVO;
import com.haeyo.biz.profession.ProfessionsMovingVO;
import com.haeyo.biz.profession.ProfessionsRepairVO;

@Repository
public class ProfessionDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<ProfessionListVO> getProList(ProfessionListVO vo) {
		System.out.println("DAO로 경로 이동");
		return mybatis.selectList("ProListResultDAO.getProList", vo);
	};
	
	public List<ProfessionListVO> getAllList(ProfessionListVO vo) {
		System.out.println("DAO로 경로 이동");
		return mybatis.selectList("ProListResultDAO.getAllList", vo);
	};
	
	public ProfessionListVO getDetail(ProfessionListVO vo) {
		System.out.println("디테일 DAO이동");
		return (ProfessionListVO)mybatis.selectOne("ProListResultDAO.proDetail", vo);
	}
	
	public List<ProfessionListVO> getReview(ProfessionListVO vo) {
		System.out.println("getReview DAO이동");
		return mybatis.selectList("ProListResultDAO.proReview", vo);
	}
	
	//서브 카테고리
	public ProfessionSubVO RepairCate(ProfessionListVO vo) {
		System.out.println("수리서브카테고리");
		return (ProfessionSubVO)mybatis.selectOne("ProSubResultDAO.RepairCate", vo);
	}
	public ProfessionSubVO MovingCate(ProfessionListVO vo) {
		System.out.println("이사서브카테고리");
		return (ProfessionSubVO)mybatis.selectOne("ProSubResultDAO.MovingCate", vo);
	}
	public ProfessionSubVO CleaningCate(ProfessionListVO vo) {
		System.out.println("청소서브카테고리");
		return (ProfessionSubVO)mybatis.selectOne("ProSubResultDAO.CleaningCate", vo);
	}
	
	
	//서브 카테고리테스트
	public ProfessionSubVO RepairCateTest(Map<String, Object> param) {
		System.out.println("수리서브카테고리");
		return (ProfessionSubVO)mybatis.selectOne("ProSubResultDAO.RepairCate", param);
	}
	public ProfessionSubVO MovingCateTest(Map<String, Object> param) {
		System.out.println("이사서브카테고리");
		return (ProfessionSubVO)mybatis.selectOne("ProSubResultDAO.MovingCate", param);
	}
	public ProfessionSubVO CleaningCateTest(Map<String, Object> param) {
		System.out.println("청소서브카테고리");
		return (ProfessionSubVO)mybatis.selectOne("ProSubResultDAO.CleaningCate", param);
	}
}
