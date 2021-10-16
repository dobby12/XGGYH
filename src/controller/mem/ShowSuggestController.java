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
import util.Paging;

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
		int genreNo = 0;
		
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
			
			genreNo = (int)session.getAttribute("genreno");
			
			System.out.println(genreNo);
		}
		else
		{
			System.out.println("로그인 정보를 가져오는데 실패했습니다.");
			return;
		}
		
	//	Paging paging = showService.getParameterPaging(req, genreNo);
	}
}
