package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XMem;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//MemberService 객체 생성
	private MemberService memberService = new MemberServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] JoinController doGet()");
		
		//VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mem/join.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] JoinController doPost()");
		
		// 요청파라미터 처리
		XMem param = memberService.getJoinMember(req);
		
		// 회원가입
		memberService.join(param);
		
		// 메인으로 리다이렉션
		resp.sendRedirect("/main");
		
	}

}
