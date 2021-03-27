package com.haeyo.biz.profession;

public class ProfessionVO {
	private int pNo;
	private int uNo;
	private int pGender;
	private String pAddress;
	private float pLocX;
	private float pLocY;
	private String certification;
	private String pPic;
	private int pAgreeCheck;
	private String pIntroduce;
	private String pCategory;
	
	private float score;
	private int count;
	

	ProfessionsCleaningVO professionsCleaningVO;
	ProfessionsRepairVO professionsRepairVO;
	ProfessionsMovingVO professionsMovingVO;
	

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getpGender() {
		return pGender;
	}

	public void setpGender(int pGender) {
		this.pGender = pGender;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public int getuNo() {
		return uNo;
	}

	public void setuNo(int uNo) {
		this.uNo = uNo;
	}

	public String getpAddress() {
		return pAddress;
	}

	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}

	public float getpLocX() {
		return pLocX;
	}

	public void setpLocX(float pLocX) {
		this.pLocX = pLocX;
	}

	public float getpLocY() {
		return pLocY;
	}

	public void setpLocY(float pLocY) {
		this.pLocY = pLocY;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getpPic() {
		return pPic;
	}

	public void setpPic(String pPic) {
		this.pPic = pPic;
	}

	public int getpAgreeCheck() {
		return pAgreeCheck;
	}

	public void setpAgreeCheck(int pAgreeCheck) {
		this.pAgreeCheck = pAgreeCheck;
	}

	public String getpIntroduce() {
		return pIntroduce;
	}

	public void setpIntroduce(String pIntroduce) {
		this.pIntroduce = pIntroduce;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	@Override
	public String toString() {
		return "ProfessionVO [pNo=" + pNo + ", uNo=" + uNo + ", pAddress=" + pAddress + ", pLocX=" + pLocX + ", pLocY="
				+ pLocY + ", certification=" + certification + ", pPic=" + pPic + ", pAgreeCheck=" + pAgreeCheck
				+ ", pIntroduce=" + pIntroduce + ", pCategory=" + pCategory + "]";
	}

}