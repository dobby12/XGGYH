package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/join/idcheck")
public class MemberIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/join/idcheck [POST]");
		
		String memid = req.getParameter("memid");
		boolean doesExist = memberService.checkId(memid);
		
		resp.getWriter().print(doesExist);
		return;
	}
}
