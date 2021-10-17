package service.face;

import java.util.List;

public interface AdminMailService {

	/**
	 * 메일 전송하기
	 * 
	 * @param memMail - 받는 사람 이메일 주소
	 * @param mailTitle - 메일 제목
	 * @param mailContent - 메일 내용
	 */
	public void sendMail(List<String> mailList, String mailTitle, String mailContent);

	/**
	 * 
	 * @param mailForPw
	 * @param mailTitle
	 * @param mailContent
	 */
	public void sendMail(String mailForPw, String mailTitle, String mailContent);
	
}
