package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/member/find")
public class MemberFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/mem/find.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if("".equals(req.getParameter("memid")) && req.getParameter("memmail")!=null) {
			System.out.println("id찾기 위한 email : "+req.getParameter("memmail"));
			String memId = memberService.getMemid(req.getParameter("memmail"));	
			if(memId!=null) {
				req.setAttribute("findid", memId);				
			} else {
				req.setAttribute("alert", "입력하신 " + req.getParameter("memmail") + "로 가입되어 있는 ID가 없습니다.");
			}
			req.setAttribute("id", true);
		} else if("".equals(req.getParameter("mememail")) && req.getParameter("memid")!=null) {
			System.out.println("pw찾기 위한 id : "+req.getParameter("memid"));			
		}
		
		req.getRequestDispatcher("/WEB-INF/views/mem/findresult.jsp").forward(req, resp);
		
	}
}
