package com.haeyo.biz.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.haeyo.biz.user.UserVO;

/**
 * 
 * @Title : 웹소켓 핸들러
 * @author : Leesumin
 * @date : 2021. 03. 31.
 */

//한글을 받고 싶어서 
public class socketHandler extends TextWebSocketHandler {
	private static final Logger logger = LoggerFactory.getLogger(socketHandler.class);

	// 로그인 한 전체
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

	// 로그인중인 개별유저
	Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();

	// 커넥션이 접속성공
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished 커넥션 접속 성공" + session);
		String senderEmail = getEmail(session);
		if(senderEmail != null) {
			users.put(senderEmail, session); //로그인 중 개별 유저 저장
		}
	}

	
	// 클라이언트가 Data전송 시
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleMessage 클라이언트 Data전송" + session);
		
		String senderEmail = getEmail(session);
		// 특정 유저에게 보내기
		String msg = message.getPayload();
		//@Payload: WebSocket에서 전송하는 데이터를 받기 위함
		if(msg != null) { //타입, 회원닉네임 
			String[] strs = msg.split(",");
			String type = strs[0];
			String uNick = strs[1];
			WebSocketSession targetSession = users.get(uNick);
			
			//실시간 접속시
			if(targetSession != null) {
				//ex) [누구님이 찜하기 하셨습니다]
				TextMessage tmpMsg = new TextMessage(uNick + "님이 회원님을 찜하셨습니다");
				targetSession.sendMessage(tmpMsg);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 소켓이 클로즈 될때
		System.out.println("afterConnectionClosed" + session);
		String senderEmail = getEmail(session);
		
		if(senderEmail!=null) {	// 로그인 값이 있는 경우만
			logger.info("연결 종료됨" + senderEmail);
			users.remove(senderEmail);
			sessions.remove(session);
		}
	}
	
	// 웹소켓 email 가져오기
	private String getEmail(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVO loginUser = (UserVO) httpSession.get("user");

		if (loginUser == null) {
			return session.getId();
		} else {
			return loginUser.getuEmail();
		}
	}

}
