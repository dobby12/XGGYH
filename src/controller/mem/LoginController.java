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

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### LoginController doGet()"); 
		
		req.getRequestDispatcher("/WEB-INF/views/mem/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### LoginController doPost()"); 
		
		//form action으로 전달된 request를 XMem객체로 반환
		XMem member = memberService.loginMem(req);
		
		//요청된 세션을 HttpSession객체로 반환
		HttpSession session = req.getSession();
		
	}
}
