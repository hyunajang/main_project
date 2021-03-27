package com.haeyo.web.board.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.haeyo.biz.user.UserVO;
import com.haeyo.biz.board.BoardVO;
import com.haeyo.biz.board.impl.TogetherBoardServiceImpl;
import com.haeyo.biz.board.impl.TogetherBoardVO;
import com.haeyo.biz.board.impl.TogetherMemberVO;
import com.haeyo.biz.board.impl.TogetherReplyVO;

@Controller
public class TogetherBoardController {
	private static final Logger logger = LoggerFactory.getLogger(TogetherBoardController.class);
	
	@Autowired
	public TogetherBoardServiceImpl togetherBoardServiceImpl;
	
	@PostMapping("/t_insertBoard.do")
	public String insertBoard(@ModelAttribute TogetherBoardVO togetherboardVO) throws Exception {
		logger.info("파라미터:"+togetherboardVO);
		togetherboardVO = togetherBoardServiceImpl.uploadBoard(togetherboardVO);
		logger.info("파라미터:"+togetherboardVO);
		togetherBoardServiceImpl.insertBoard(togetherboardVO);
		return "redirect:t_getBoardMain.do";
	}

	@PostMapping("/t_updateBoard.do")
	public String updateBoard(@ModelAttribute TogetherBoardVO togetherboardVO) throws Exception {
		System.out.println("TogetherBoardController updateBoard처리");
		togetherboardVO = togetherBoardServiceImpl.uploadBoard(togetherboardVO);
		System.out.println(togetherboardVO);
		togetherBoardServiceImpl.updateBoard(togetherboardVO);
		return "redirect:t_getBoardMain.do";
	}

	@RequestMapping("/t_deleteBoard.do")
	public String deleteBoard(@RequestParam("tNo") int tNo) {
		logger.info("");
		togetherBoardServiceImpl.deleteBoard(tNo);
		return "redirect:t_getBoardMain.do";
	}

	@RequestMapping(value="/t_getBoard.do", method=RequestMethod.GET)
	public String getBoard(@RequestParam("tNo") int tNo, Model model) {
		logger.info("파라미터"+tNo);
		model.addAttribute("board",togetherBoardServiceImpl.getBoard(tNo));
		model.addAttribute("ReplyList", togetherBoardServiceImpl.getReply(tNo));
		return "togetherBoard";
	}
	
	//main=>거리외에는 조건 없는 리스트
	@RequestMapping("/t_getBoardMain.do")
	public String getBoardMain(Model model, HttpSession session) {
		logger.info("");
		UserVO userVO = new UserVO();
//		int uNo = session.getAttribute("");
//		userVO.setuNo(uNo);
		userVO.setuNo(10001);
		model.addAttribute("boardList",togetherBoardServiceImpl.getBoardList(userVO));
		return "togetherMain";
	}
	
//	//카테고리별 게시글 리스트
//	@RequestMapping("/t_getBoardList.do")
//	public String getBoardList(Model model, HttpSession session) {
//		UserVO userVO = new UserVO();
////		int uNo = session.getAttribute("");
////		userVO.setuNo(uNo);
//		userVO.setuNo(10001);
//		model.addAttribute("boardList",togetherBoardServiceImpl.getBoardList(userVO));
//		return "togetherList";
//	}
	
	@RequestMapping("/t_getWritingBoard.do")
	public String getWritingBoard(Model model) {
		return "togetherInsertBoard";
	}
	
	@RequestMapping("/t_getUpdateBoard.do")
	public String getUpdateBoard(Model model, @RequestParam int tNo) {
		model.addAttribute("Board",togetherBoardServiceImpl.getBoard(tNo));
		return "togetherUpdateBoard";
	}
	
	//댓글에 대한 부분
	
	@RequestMapping("/t_insertReply.do")
	@ResponseBody  //자바객체를 응답(json)본문의 객체로 변환
	public TogetherReplyVO insertReply(@ModelAttribute TogetherReplyVO togetherReplyVO) {
		logger.info("togetherReplyVO:"+togetherReplyVO);
		return togetherBoardServiceImpl.insertReply(togetherReplyVO);
	}
	
	@RequestMapping("/t_updateReply.do")
	@ResponseBody
	public TogetherReplyVO updateReply(@ModelAttribute TogetherReplyVO togetherReplyVO) {
		logger.info("togetherReplyVO:"+togetherReplyVO);
		return togetherBoardServiceImpl.updateReply(togetherReplyVO);
	}
	
	@RequestMapping("/t_deleteReply.do")
	@ResponseBody
	public int deleteReply(@RequestParam int trNo) {
		logger.info("trNo:"+trNo);
		int result = togetherBoardServiceImpl.deleteReply(trNo);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/t_joinNow.do")
	@ResponseBody
	public TogetherBoardVO joinNow(@ModelAttribute TogetherMemberVO togetherMemberVO, HttpSession session) {
		System.out.println("TogetherBoardController joinNow처리");
//		int uNo = (int) session.getAttribute("");
//		togetherMemberVO.setgNo(uNo);
		togetherMemberVO.setgNo(10001);
		return togetherBoardServiceImpl.JoinNow(togetherMemberVO);
	}
	
	@RequestMapping("/t_checkBookmark.do")
	@ResponseBody
	public int checkBookmark(@ModelAttribute TogetherBoardVO togetherBoardVO, HttpSession session) {
		logger.info("TogetherBoardVO"+togetherBoardVO);
//		int uNo = (int) session.getAttribute("");
		int uNo = 10001;
		return togetherBoardServiceImpl.checkBookmark(togetherBoardVO, uNo);
	}
}
