package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/join/emailcheck")
public class MemberEmailCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/join/emailcheck [POST]");
		
		String memmail = req.getParameter("memmail");
		boolean doesExist = memberService.checkEmail(memmail);
		
		resp.getWriter().print(doesExist);
		return;
	}
}
