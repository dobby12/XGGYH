package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.XMem;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/join")
public class MemberJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//MemberService 객체 생성
	private MemberService memberService = new MemberServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] JoinController doGet()");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		//VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mem/join/join.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] JoinController doPost()");
		
		// 요청파라미터 처리
		XMem param = memberService.getJoinMember(req);

		System.out.println(param);
		
		// 회원가입
		try {
			memberService.join(param);
			
			resp.getWriter().print(true);
		} catch(Exception e) {
			resp.getWriter().print(false);
		}
		
		return;
	}

}
