package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/join/nickcheck")
public class MemberNickCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/join/nickcheck [POST]");
		
		String memnick = req.getParameter("memnick");
		boolean doesExist = memberService.checkNick(memnick);
		
		resp.getWriter().print(doesExist);
		return;
	}
}
