package com.haeyo.biz.profession;

import java.util.List;
import java.util.Map;

public interface ProfessionService {
	List<ProfessionListVO> getProList(ProfessionListVO vo);
	List<ProfessionListVO> getAllList(ProfessionListVO vo);
	ProfessionListVO getDetail(ProfessionListVO vo);
	List<ProfessionListVO> getReview(ProfessionListVO vo);
	
	//카테고리 별 소카테고리 분류
	ProfessionSubVO getSubCate(ProfessionListVO vo);
	ProfessionSubVO getSubCateTest(Map<String, Object> param);
}
