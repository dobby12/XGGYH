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
		
		XMem mem = memberService.getLoginMem(req);
		System.out.println("###TEST### mem : " + mem);
		HttpSession session = req.getSession();
		
		if(memberService.loginMem(mem)) {
			session.setAttribute("login", true);
			session.setAttribute("memid", memberService.getMem(mem).getMem_id());
			session.setAttribute("memnick", memberService.getMem(mem).getMem_nick());
			req.getRequestDispatcher("/WEB-INF/views/mem/main.jsp").forward(req, resp);
		} else {
			session.setAttribute("loginfail", true);
			req.getRequestDispatcher("/WEB-INF/views/mem/login.jsp").forward(req, resp);
		}
		
	}
}
