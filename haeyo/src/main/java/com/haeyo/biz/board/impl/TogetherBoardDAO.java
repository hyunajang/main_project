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
import com.haeyo.web.board.impl.TogetherBoardController;

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
	
	public List<BoardVO> getBoardList(UserVO userVO) { //uNo, tHeader, pagecount (, TogetherBoardVO togetherBoardVO
		logger.info("userVO:"+userVO);
		System.out.println(sqlSession.selectList("togetherBoardDAO.selectTogetherBoardList", userVO));
		return sqlSession.selectList("togetherBoardDAO.selectTogetherBoardList", userVO); // ListVO를 이용하는 것 같음
//		리턴totalcount=>count(t_no)
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
	
	public int insertBookmark(TogetherBoardVO togetherBoardVO, int uNo) {
		logger.info("togetherBoardVO:"+togetherBoardVO+", uNo"+uNo);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("togetherBoardVO", togetherBoardVO);
		params.put("uNo", uNo);
		return sqlSession.insert("togetherBoardDAO.insertBookmark", params);
	}
	
	public int deleteBookmark(int uNo) {
		logger.info("uNo:"+uNo);
		return sqlSession.update("togetherBoardDAO.deleteBookmark", uNo);
	}
}
