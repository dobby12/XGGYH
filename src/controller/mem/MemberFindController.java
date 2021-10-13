package controller.mem;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AdminMailService;
import service.face.MemberService;
import service.impl.AdminMailServiceImpl;
import service.impl.MemberServiceImpl;

@WebServlet("/member/find")
public class MemberFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	private AdminMailService adminMailService = new AdminMailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/mem/find.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String mailForId = req.getParameter("mailForId");
		String mailForPw = req.getParameter("mailForPw");

		//ID 찾기
		if("".equals(mailForPw) && mailForId!=null) {
			String idForMail = memberService.getMemid(mailForId);	
			
			//입력한 메일이 DB에 있을 경우
			if(idForMail!=null) {
				req.setAttribute("findid", idForMail);
				
			//입력한 메일이 DB에 없을 경우
			} else {
				req.setAttribute("noMailId", "입력하신 " + mailForId + "로 가입된 회원 정보가 없습니다.");
			}
			
			//ID 찾기를 실행
			req.setAttribute("id", true);
		
		//PW 찾기
		} else if("".equals(mailForId) && mailForPw!=null) {
			String idForMail = memberService.getMemid(mailForPw);
			
			//입력한 메일이 DB에 있을 경우
			if(idForMail!=null) {
				
				//uuid를 통한 PW 변경
				UUID uuid = UUID.randomUUID();
				String uuidPw = uuid.toString().split("-")[0];
				
				memberService.setMempwUpdate(mailForPw, uuidPw);
				
				//보낼 메일 제목
				String mailTitle = "[공공연희] 재설정한 "+idForMail+"님의 패스워드";
				
				//보낼 메일 내용
				String mailContent = "안녕하세요. 귀하의 비밀번호는 ["+uuidPw+"]으로 변경되었습니다.";
				
				//메일 발송
				adminMailService.sendMail(mailForPw, mailTitle, mailContent);
			
			//입력한 메일이 DB에 없을 경우
			} else {
				req.setAttribute("noMailPw", "입력하신 " + mailForPw + "로 가입된 회원 정보가 없습니다.");
			}
			
			//PW 찾기를 실행
			req.setAttribute("pw", true);			
		}
		
		req.getRequestDispatcher("/WEB-INF/views/mem/findresult.jsp").forward(req, resp);
		
	}
}
