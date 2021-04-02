package com.haeyo.web.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haeyo.biz.user.UserVO;
import com.haeyo.biz.board.BoardVO;
import com.haeyo.biz.board.impl.PagingVO;
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
		String tHeader = togetherboardVO.gettHeader();
		return "redirect:t_openBoardList.do";
	}

	@PostMapping("/t_updateBoard.do")
	public String updateBoard(@ModelAttribute TogetherBoardVO togetherboardVO) throws Exception {
		System.out.println("TogetherBoardController updateBoard처리");
		togetherboardVO = togetherBoardServiceImpl.uploadBoard(togetherboardVO);
		System.out.println(togetherboardVO);
		togetherBoardServiceImpl.updateBoard(togetherboardVO);
		String tHeader = togetherboardVO.gettHeader();
		return "redirect:t_openBoardList.do";
	}

	@RequestMapping("/t_deleteBoard.do")
	public String deleteBoard(@RequestParam("tNo") int tNo) {
		logger.info("");
		togetherBoardServiceImpl.deleteBoard(tNo);
		return "redirect:t_openBoardList.do";
	}

	@RequestMapping(value="/t_getBoard.do", method=RequestMethod.GET)
	public String getBoard(@RequestParam("tNo") int tNo, Model model) {
		logger.info("파라미터"+tNo);
		model.addAttribute("board",togetherBoardServiceImpl.getBoard(tNo));
		model.addAttribute("ReplyList", togetherBoardServiceImpl.getReply(tNo));
		return "togetherBoard";
	}
	
	//main=>종합 리스트
	@RequestMapping("/t_getBoardMain.do")
	public String getBoardMain(Model model, HttpSession session, String tHeader) {
		logger.info("");
		UserVO userVO = new UserVO();
//		int uNo = session.getAttribute("user");
//		userVO.setuNo(uNo);
		userVO.setuNo(10001);
		
		PagingVO pagingVO = new PagingVO();
		model.addAttribute("boardList",togetherBoardServiceImpl.getBoardList(userVO, tHeader, pagingVO));
		return "togetherMain"; //jsp로 이동
	}
	
	//카테고리별 게시글 리스트
	@RequestMapping("/t_getBoardList.do")
	@ResponseBody
	public Map<String,Object> getBoardList(Model model, HttpSession session, @RequestParam String tHeader,
			@RequestParam int nowPage ) {
		logger.info("tHeader: " + tHeader+", nowPage: "+nowPage);
		UserVO userVO = new UserVO();
//		int uNo = session.getAttribute("user");
//		userVO.setuNo(uNo);
		userVO.setuNo(10001);
		
		int total = togetherBoardServiceImpl.countTotalBoard();
		if(nowPage == 0) {
			nowPage = 1;
		}
		
		PagingVO pagingVO = new PagingVO(nowPage, total);	
		List<BoardVO> boardList = togetherBoardServiceImpl.getBoardList(userVO, tHeader, pagingVO);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("paging", pagingVO);
		result.put("boardList", boardList);
		return result; //ajax방식으로 전달
	}
	
	@RequestMapping("/t_openBoardList.do")
	public String openBoardList(Model model, String tHeader) {
		logger.info("tHeader: " + tHeader);
		model.addAttribute("tHeader", tHeader);
		return "togetherBoardList";
	}
	
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
	@ResponseBody 
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
	
	//모임참여
	@RequestMapping("/t_joinNow.do")
	@ResponseBody
	public TogetherBoardVO joinNow(@ModelAttribute TogetherMemberVO togetherMemberVO, HttpSession session) {
		System.out.println("TogetherBoardController joinNow처리");
//		int uNo = (int) session.getAttribute("user");
//		togetherMemberVO.setgNo(uNo);
		togetherMemberVO.setgNo(10001);
		return togetherBoardServiceImpl.JoinNow(togetherMemberVO);
	}
	
	//북마크
	@RequestMapping("/t_checkBookmark.do")
	@ResponseBody
	public int checkBookmark(@RequestParam int tNo, @RequestParam int bookmark, HttpSession session) {
//		int uNo = (int) session.getAttribute("user");
		int uNo = 10001;
		logger.info("tNo: "+tNo+",tNo: "+ bookmark+",uNO: "+ uNo);
		return togetherBoardServiceImpl.checkBookmark(tNo, bookmark, uNo);
	}
	
	//검색창
	@RequestMapping("/t_searchBoardList.do")
	@ResponseBody
	public List<TogetherBoardVO> searchBoardList(@RequestParam String searchInput){
		logger.info("searchInput: "+searchInput);
		return togetherBoardServiceImpl.searchBoardList(searchInput);
	}
	
	//검색창 자동완성
	@RequestMapping("/t_previewBoardList.do")
	@ResponseBody
	public List<TogetherBoardVO> previewBoardList(@RequestParam String searchInput){
		logger.info("searchInput: "+searchInput);
		return togetherBoardServiceImpl.previewBoardList(searchInput);
	}
	
	//분류별 정렬
	@RequestMapping("/t_sortBoardList.do")
	@ResponseBody
	public List<TogetherBoardVO> sortBoardList(@RequestParam String sortInput , @RequestParam String tHeader){
		logger.info("sortInput: "+sortInput+", tHeader: "+ tHeader);
		return togetherBoardServiceImpl.sortBoardList(sortInput, tHeader);
	}
	
}
