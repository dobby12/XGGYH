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
import service.face.ShowService;
import service.impl.MemberServiceImpl;
import service.impl.ShowServiceImpl;

/**
 * Servlet implementation class ShowSuggestController
 */
@WebServlet("/show/memgenre")
public class ShowSuggestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	private ShowService showService = new ShowServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = null;
		
		if(req.getSession() != null)
		{
			session = req.getSession();
		}
		else
		{
			return;
		}
		
		XMem mem = memberService.getLoginMem(req);
		
		if(memberService.loginMem(mem)) {
			session.setAttribute("genreno", memberService.getMem(mem).getGenreNo());
		}
	}
}
