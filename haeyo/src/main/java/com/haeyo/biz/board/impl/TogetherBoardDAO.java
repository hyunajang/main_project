package com.haeyo.biz.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haeyo.biz.board.BoardVO;
import com.haeyo.biz.user.UserVO;

@Repository
public class TogetherBoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(TogetherBoardDAO.class);
	
	@Autowired
	public SqlSessionTemplate sqlSession;
	//게시글에 대한 부분
	public void insertBoard(BoardVO vo) {
		sqlSession.insert("togetherBoardDAO.insertTogetherBoard", vo);
	}
	
	public void updateBoard(BoardVO vo) {
		logger.info("vo:"+vo);
		sqlSession.update("togetherBoardDAO.updateTogetherBoard", vo);
	}
	
	public void deleteBoard(int tNo) {
		logger.info("tNo:"+tNo);
		sqlSession.delete("togetherBoardDAO.deleteTogetherBoard", tNo);
	}
	
	public TogetherBoardVO getBoard(int tNo) {
		logger.info("tNo:"+tNo);
		System.out.println((TogetherBoardVO)sqlSession.selectOne("togetherBoardDAO.selectTogetherBoard", tNo));
		return (TogetherBoardVO)sqlSession.selectOne("togetherBoardDAO.selectTogetherBoard", tNo);
	}

	public List<BoardVO> getBoardList(UserVO userVO, String tHeader, PagingVO pagingVO) { //uNo, tHeader, pagecount (, TogetherBoardVO togetherBoardVO
		logger.info(" userVO: "+userVO+" tHeader:"+tHeader);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userVO", userVO);
		params.put("tHeader", tHeader);
		params.put("pagingVO", pagingVO);
		List<BoardVO> result = sqlSession.selectList("togetherBoardDAO.selectTogetherBoardList", params);
		logger.info("result: "+ result);
		return result; // ListVO를 이용하는 것 같음
//		리턴totalcount=>count(t_no)
	}
	
	public int countTotalBoard() {
		logger.info("");
		return sqlSession.selectOne("countTotalBoard");
	}
	
	//댓글에 대한 부분
	public List<TogetherReplyVO> getReply(int tNo) {
		logger.info("tNo:"+tNo);
		return sqlSession.selectList("togetherBoardDAO.selectReply", tNo);
	}
	
	public TogetherReplyVO getReplyOne(int trNo) {
		logger.info("trNo:"+trNo);
		TogetherReplyVO result = sqlSession.selectOne("togetherBoardDAO.selectReplyOne", trNo);
		logger.info("result: "+result);
		return result;
	}
	
	public int insertReply(TogetherReplyVO togetherReplyVO) {
		System.out.println("TogetherBoardDAO insertSelectReply처리");
		sqlSession.insert("togetherBoardDAO.insertReply", togetherReplyVO);
		int trNo = togetherReplyVO.getTrNo();
		System.out.println("trNo = " + trNo);
		return trNo;
	}
	
	public void updateReply(TogetherReplyVO togetherReplyVO) {
		logger.info("togetherReplyVO:"+togetherReplyVO);
		sqlSession.update("togetherBoardDAO.updateReply", togetherReplyVO);
	}
	
	public int deleteReply(int trNo) {
		System.out.println("TogetherBoardDAO deleteReply처리");
		return sqlSession.delete("togetherBoardDAO.deleteReply", trNo);
	}
	
	//모임 참여에 대한 부분
	public int updateJoinNow(TogetherMemberVO togetherMemberVO) {
		System.out.println("TogetherBoardDAO updateJoinNow처리");
		int success = sqlSession.update("togetherBoardDAO.updateJoinNow", togetherMemberVO);
		System.out.println(success);
		return success;
	}
	
	public int insertJoinNow(TogetherMemberVO togetherMemberVO) {
		System.out.println("TogetherBoardDAO insertJoinNow처리");
		int success = sqlSession.insert("togetherBoardDAO.insertJoinNow", togetherMemberVO);
		System.out.println(success);
		return success;
	}
	
	public TogetherBoardVO getJoinNow(TogetherMemberVO togetherMemberVO) {
		 TogetherBoardVO result = sqlSession.selectOne("togetherBoardDAO.selectJoinNow", togetherMemberVO);
		 System.out.println(result);
		 return result;
	}
	
	//북마크에 대한 부분
	public int updateBookmark(TogetherBoardVO togetherBoardVO) {
		logger.info("togetherBoardVO"+togetherBoardVO);
		return sqlSession.update("togetherBoardDAO.updateBookmark", togetherBoardVO);
	}
	
	public int insertBookmark(int tNo, int uNo) {
		logger.info("tNo:"+tNo+", uNo"+uNo);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tNo", tNo);
		params.put("uNo", uNo);
		return sqlSession.insert("togetherBoardDAO.insertBookmark", params);
	}
	
	public int deleteBookmark(int tNo, int uNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tNo", tNo);
		params.put("uNo", uNo);
		return sqlSession.update("togetherBoardDAO.deleteBookmark", params);
	}
	
	//검색에 대한 부분
	public List<TogetherBoardVO> searchBoardList(String searchInput) {
		logger.info("searchInput: "+searchInput);
		List<TogetherBoardVO> result = sqlSession.selectList("togetherBoardDAO.searchBoardList", searchInput);
		logger.info("result: "+result);
		return result;
	}
	//검색 자동 완성
	public List<TogetherBoardVO> previewBoardList(String searchInput) {
		logger.info("searchInput: "+searchInput);
		List<TogetherBoardVO> result = sqlSession.selectList("togetherBoardDAO.previewBoardList", searchInput);
		logger.info("result: "+result);
		return result;
	}
	//분류별 정렬
	public List<TogetherBoardVO> sortBoardList(String sortInput, String tHeader){
		logger.info("sortInput: "+sortInput+",tHeader: "+ tHeader);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sortInput", sortInput);
		params.put("tHeader", tHeader);
		List<TogetherBoardVO> result = sqlSession.selectList("togetherBoardDAO.sortBoardList", params);
		logger.info("result: "+result);
		return result;
	}
}
