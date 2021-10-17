package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XMem;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/mypage/myinfo/update")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/myinfo/update [GET]");

		//로그인한 회원아이디
		String memid = (String)req.getSession().getAttribute("memid");
		
		//상세보기
		XMem updateMem = memberService.getUpdate(memid);
		System.out.println(updateMem);

		//조회결과값 전달
		req.setAttribute("updateMem", updateMem);
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}

		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/mem/update.jsp").forward(req, resp);		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/myinfo/update  [POST]");

		try {
			memberService.updateMem(req);
			
			resp.getWriter().print(true);
		} catch(Exception e) {
			resp.getWriter().print(false);
		}
		
		return;
	}
	
}
