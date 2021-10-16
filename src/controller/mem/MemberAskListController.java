package controller.mem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XAsk;
import service.face.AskService;
import service.impl.AskServiceImpl;
import util.Paging;

@WebServlet("/mypage/myask")
public class MemberAskListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AskService askService = new AskServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//로그인한 회원아이디
		String memid = (String)req.getSession().getAttribute("memid");
		
		Paging paging = askService.getPagingByMemId(req, memid);
		
		//로그인한 회원아이디가 작성한 문의 객체
		List<XAsk> memidAskList = askService.getAskListByMemid(paging, memid);
		req.setAttribute("memidAskList", memidAskList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/mypage/myask");

		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/ask/list.jsp").forward(req, resp);

	}
}
