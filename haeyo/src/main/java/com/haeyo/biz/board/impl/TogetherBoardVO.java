package com.haeyo.biz.board.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.haeyo.biz.board.BoardVO;
import com.haeyo.biz.user.UserVO;

public class TogetherBoardVO implements BoardVO{
	
	private int tNo;
	private int hNo;
	private String tHeader;
	private String tTitle;
	private String tContent;
	private String tPic;
	private String tAddress;
	private float tLocX;
	private float tLocY;
	private int tBookmark;
	private Date tCredate;
	private Date tJoinDate;
	private int tJoinMax;
	private int tJoinNow;
	private int tHits;
	
	//파일업로드
	private MultipartFile uploadFile;
	//사용자 정보
	private UserVO userVO;
	//댓글
	private List<TogetherReplyVO> tReplyList;
	//북마크
	private TogetherBookmarkVO tBookmarkVO;
	
	public int gettNo() {
		return tNo;
	}
	public void settNo(int tNo) {
		this.tNo = tNo;
	}
	public int gethNo() {
		return hNo;
	}
	public void sethNo(int hNo) {
		this.hNo = hNo;
	}
	public String gettHeader() {
		return tHeader;
	}
	public void settHeader(String tHeader) {
		this.tHeader = tHeader;
	}
	public String gettTitle() {
		return tTitle;
	}
	public void settTitle(String tTitle) {
		this.tTitle = tTitle;
	}
	public String gettContent() {
		return tContent;
	}
	public void settContent(String tContent) {
		this.tContent = tContent;
	}
	public String gettPic() {
		return tPic;
	}
	public void settPic(String tPic) {
		this.tPic = tPic;
	}
	public String gettAddress() {
		return tAddress;
	}
	public void settAddress(String tAddress) {
		this.tAddress = tAddress;
	}
	public float gettLocX() {
		return tLocX;
	}
	public void settLocX(float tLocX) {
		this.tLocX = tLocX;
	}
	public float gettLocY() {
		return tLocY;
	}
	public void settLocY(float tLocY) {
		this.tLocY = tLocY;
	}
	public int gettBookmark() {
		return tBookmark;
	}
	public void settBookmark(int tBookmark) {
		this.tBookmark = tBookmark;
	}
	public Date gettCredate() {
		return tCredate;
	}
	public void settCredate(Date tCredate) {
		this.tCredate = tCredate;
	}
	public Date gettJoinDate() {
		return tJoinDate;
	}
	public void settJoinDate(Date tJoinDate) {
		this.tJoinDate = tJoinDate;
	}
	public int gettJoinMax() {
		return tJoinMax;
	}
	public void settJoinMax(int tJoinMax) {
		this.tJoinMax = tJoinMax;
	}
	public int gettJoinNow() {
		return tJoinNow;
	}
	public void settJoinNow(int tJoinNow) {
		this.tJoinNow = tJoinNow;
	}
	public int gettHits() {
		return tHits;
	}
	public void settHits(int tHits) {
		this.tHits = tHits;
	}
	
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	public List<TogetherReplyVO> gettReplyList() {
		return tReplyList;
	}
	public void settReplyList(List<TogetherReplyVO> tReplyList) {
		this.tReplyList = tReplyList;
	}
	public TogetherBookmarkVO gettBookmarkVO() {
		return tBookmarkVO;
	}
	public void settBookmarkVO(TogetherBookmarkVO tBookmarkVO) {
		this.tBookmarkVO = tBookmarkVO;
	}
	@Override
	public String toString() {
		return "TogetherBoardVO [tNo=" + tNo + ", hNo=" + hNo + ", tHeader=" + tHeader + ", tTitle=" + tTitle
				+ ", tContent=" + tContent + ", tPic=" + tPic + ", tAddress=" + tAddress + ", tLocX=" + tLocX
				+ ", tLocY=" + tLocY + ", tBookmark=" + tBookmark + ", tCredate=" + tCredate + ", tJoinDate="
				+ tJoinDate + ", tJoinMax=" + tJoinMax + ", tJoinNow=" + tJoinNow + ", tHits=" + tHits + ", userVO="
				+ userVO + ", tReplyList=" + tReplyList + ", togetherBookmarkVO="
				+ tBookmarkVO + "]";
	}
}
