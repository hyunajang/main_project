package com.haeyo.web.profession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.haeyo.biz.profession.ProfessionListVO;
import com.haeyo.biz.profession.ProfessionService;
import com.haeyo.biz.profession.ProfessionSubVO;

@RestController
public class ProfessionSubController {
	@Autowired
	private ProfessionService professionService;
	
	@RequestMapping(value="/subCategory.do", method=RequestMethod.GET, produces="application/text;charset=utf-8")
	@ResponseBody
	public String getSubCate(ProfessionListVO vo) throws JsonProcessingException {
		System.out.println(professionService.getSubCate(vo));
//		Gson gson = new Gson();
//		String subCate = gson.toJson(professionService.getSubCate(vo));
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(professionService.getSubCate(vo));
		return jsonString;
	}
	
	@GetMapping("/subCateTest.do")
	@ResponseBody
	public ProfessionSubVO getSubCate(@RequestBody  Map<String
			, Object> param) {
		Map<String, Object> condition = new HashMap<String, Object>();
		
		//json string으로 들어온 파라미터 형변환
		int pNo = Integer.valueOf(param.get("pNo").toString());
		String pCategory = param.get("pCategory").toString();
		
		condition.put("pNo", pNo);
		condition.put("pCategory", pCategory);
		
		ProfessionSubVO professionSubVO = professionService.getSubCateTest(condition);
		
		return professionSubVO;
	}
	
//	@RequestMapping(value="/test.do", method=RequestMethod.GET, produces="application/text;charset=utf-8")
//	@ResponseBody
//	public String test(ProfessionSubVO vo) {
////	String pNo = vo.get("pNo").toString();
//	System.out.println(vo);
//	return "성공";
//	}


}
