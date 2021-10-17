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

@WebServlet("/mypage/myinfo/delete")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/myinfo/delete [GET]");

		//로그인한 회원아이디
		String memid = (String)req.getSession().getAttribute("memid");
		
		XMem mem = memberService.getMyInfo(memid);
		System.out.println(mem);
		
		req.setAttribute("mem", mem);
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/mem/delete.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//로그인한 회원아이디
		String memid = (String)req.getSession().getAttribute("memid");
		
		XMem mem = memberService.getMyInfo(memid);
		memberService.setMemDelete(mem);
		
		HttpSession session = req.getSession();
		session.invalidate();
		resp.sendRedirect("/");
		
	}
}
