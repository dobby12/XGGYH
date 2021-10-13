package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AskService;
import service.impl.AskServiceImpl;

@WebServlet("/mypage/myask/write")
public class MemberAskWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//AskService 객체
	private AskService askService = new AskServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/myinfo/write [GET]");
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/ask/write.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/myinfo/write [POST]");
		
		//작성글 삽입
		askService.write(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/mypage/myask");
		
	}

}
