
package com.haeyo.biz.board.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.haeyo.biz.board.BoardVO;
import com.haeyo.biz.user.UserVO;

@Service("TogetherBoardService")
public class TogetherBoardServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(TogetherBoardServiceImpl.class);
	
	@Autowired
	public TogetherBoardDAO togetherBoardDAO;
	
	@Autowired
	HttpSession httpSession;
	
	public void insertBoard(BoardVO boardVO) {
		togetherBoardDAO.insertBoard(boardVO);
	}

	public void updateBoard(BoardVO boardVO) {
		togetherBoardDAO.updateBoard(boardVO);
	}

	public void deleteBoard(int tNo) {
		togetherBoardDAO.deleteBoard(tNo);

	}

	public TogetherBoardVO getBoard(int tNo) {
		logger.info("tNo:"+tNo);
		return togetherBoardDAO.getBoard(tNo);
	}

	public List<BoardVO> getBoardList(UserVO userVO, String tHeader, PagingVO pagingVO) {
		logger.info("userVO:"+userVO+"tHeader:"+tHeader);
		return togetherBoardDAO.getBoardList(userVO, tHeader, pagingVO);
	}
	
	public int countTotalBoard() {
		return togetherBoardDAO.countTotalBoard();
	}

	public TogetherBoardVO uploadBoard(TogetherBoardVO togetherBoardVO) throws Exception {
		logger.info("togetherBoardVO"+ togetherBoardVO);
		MultipartFile uploadFile = togetherBoardVO.getUploadFile(); //파일 업로드 객체 생성
		logger.info("uploadFile" + uploadFile);
		System.out.println("uploadBoard() 처리 중");
		if(!uploadFile.isEmpty()) {
			String tPic = uploadFile.getOriginalFilename();
			String rootPath = httpSession.getServletContext().getRealPath("/");
			String attachPath ="/WEB-INF/boardImages/";
			System.out.println(rootPath+attachPath+tPic);
			uploadFile.transferTo(new File(rootPath+attachPath+tPic)); //해당 경로에 받은 이름의 파일을 만들어 저장
			togetherBoardVO.settPic(tPic);
		}
		return togetherBoardVO;
	}
	
	public List<TogetherReplyVO> getReply(int tNo) {
		System.out.println("TogetherBoardService에서 getBoardReply 처리");
		return togetherBoardDAO.getReply(tNo);
	}
	
	public TogetherReplyVO insertReply(TogetherReplyVO togetherReplyVO) {
		System.out.println("TogetherBoardServiceImpl insertReply처리");
		int trNo = togetherBoardDAO.insertReply(togetherReplyVO);
		return togetherBoardDAO.getReplyOne(trNo);
	}
	
	public TogetherReplyVO updateReply(TogetherReplyVO togetherReplyVO) {
		logger.info("");
		togetherBoardDAO.updateReply(togetherReplyVO);
		return togetherBoardDAO.getReplyOne(togetherReplyVO.getTrNo());
	}
	
	public int deleteReply(int trNo) {
		System.out.println("TogetherBoardServiceImpl deleteReply처리");
		return togetherBoardDAO.deleteReply(trNo);
	}
	
	public TogetherBoardVO JoinNow(TogetherMemberVO togetherMemberVO) {
		System.out.println("TogetherBoardServiceImpl JoinNow처리");
		int updateSuccess = togetherBoardDAO.updateJoinNow(togetherMemberVO);
		int insertSuccess = togetherBoardDAO.insertJoinNow(togetherMemberVO);
		System.out.println("updateSuccess: "+ updateSuccess+", insertSuccess: "+insertSuccess);
		return togetherBoardDAO.getJoinNow(togetherMemberVO);
	}
	
	public int checkBookmark(int tNo, int boomark, int uNo) {
		logger.info("tNo"+tNo+",uNo: "+uNo+", boomark: "+boomark);
		int Success = 0;
		
		// 이미 체크된 북마크를 다시 누른 상황
		if(boomark == 1) {
			int deleteSuccess = togetherBoardDAO.deleteBookmark(tNo, uNo);
			logger.info("deleteSuccess: "+deleteSuccess);
		// 체크가 안된 북마크를 누른 상황
		}else if(boomark == 0) {
			int insertSuccess = togetherBoardDAO.insertBookmark(tNo, uNo);
			logger.info("insertSuccess: "+insertSuccess);
			Success = 1;
		}
		return Success;
	}
	
	//검색에 대한 부분
	public List<TogetherBoardVO> searchBoardList(String searchInput) {
		logger.info("searchInput: "+searchInput);
		return  togetherBoardDAO.searchBoardList(searchInput);
	} 
	
	public List<TogetherBoardVO> previewBoardList(String searchInput) {
		logger.info("searchInput: "+searchInput);
		return  togetherBoardDAO.previewBoardList(searchInput);
	} 
	
	public List<TogetherBoardVO> sortBoardList(String sortInput, String tHeader){
		logger.info("sortInput: "+sortInput+", tHeader: "+tHeader);
		return togetherBoardDAO.sortBoardList(sortInput, tHeader);
	}
}
