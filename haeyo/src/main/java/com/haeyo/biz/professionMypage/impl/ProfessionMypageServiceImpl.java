package com.haeyo.biz.professionMypage.impl;



import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.haeyo.biz.board.impl.TogetherBoardServiceImpl;
import com.haeyo.biz.profession.ProfessionVO;
import com.haeyo.biz.user.UserVO;


@Service("ProfessionMypageService")
public class ProfessionMypageServiceImpl implements ProfessionMypageService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProfessionMypageServiceImpl.class);
	
	@Autowired
	private ProfessionMypageDAO professionMypageDao;
	
	@Autowired
	HttpSession httpSession;
	
	
	@Override
	public void updateProfile(ProfessionVO vo, MultipartHttpServletRequest request) throws Exception{
		System.out.println("ProfessionMypageService에서 updateProfile 기능 처리");
		
		String pPic = "";
		String cert = ""; 
		
		Iterator<String> files = request.getFileNames();
		while(files.hasNext()) {
			String file = files.next();
			
			MultipartFile uploadFile = request.getFile(file);
			if(!uploadFile.isEmpty()) {
				String paramName = uploadFile.getName();
				String fileName = uploadFile.getOriginalFilename();
				String rootPath = httpSession.getServletContext().getRealPath("/");
				String attachPath = "/resources/image/";
				uploadFile.transferTo(new File(rootPath+attachPath+fileName));
				logger.info("파일 이름: " + paramName + ":"+ fileName);
				
				if(paramName.equals("file_pic")) {
					pPic = fileName; 
				}else if(paramName.equals("file_cert")) {
					cert = fileName;
				}
			}
			
		}
		vo.setpPic(pPic);
		vo.setCertification(cert);
		logger.info("ProfessionVO : " + vo);
		
		professionMypageDao.updateProfile(vo);
	}
	
	@Override
	public void deleteProfile(int pNo) {
		System.out.println("ProfessionMypageService에서 deleteProfile 기능 처리");
		professionMypageDao.deleteProfile(pNo);
		
	}

	@Override
	public ProfessionVO getProfile(UserVO userVO) {
		System.out.println("ProfessionMypageService에서 getProfile(a) 기능 처리");
		return (ProfessionVO) professionMypageDao.getProfile(userVO);
	}

//	@Override
//	public ProfessionVO uploadFiles(ProfessionVO vo) throws Exception {
//		
//		
//		List<MultipartFile> fileList = vo.getUploadFiles();
//		if(!fileList.isEmpty()) {
//			
//				String fileName = uploadFile.getOriginalFilename();
//				String rootPath = httpSession.getServletContext().getRealPath("/");
//				String attachPath = "/resources/image/";
//				uploadFile.transferTo(new File(rootPath+attachPath+fileName));
//				System.out.println("uploadFile : " + fileName);
//			
//		}
//		
//			return ProfessionVO;
//	}

	
		
}
