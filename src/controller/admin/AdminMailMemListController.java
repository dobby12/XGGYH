package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XMem;
import service.face.AdminMemberService;
import service.impl.AdminMemberServiceImpl;
import util.Paging;

@WebServlet("/admin/mail/mem/list")
public class AdminMailMemListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMemberService adminMemberService = new AdminMemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/list [GET]");
		
		Paging paging = adminMemberService.getPaging(req);
		
		//회원 정보 전체 조회
		List<XMem> memList = adminMemberService.getMemList(paging);
		
		req.setAttribute("memList", memList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/mail/mem/list");
		
		req.getRequestDispatcher("/WEB-INF/views/admin/mail/mem/list.jsp").forward(req, resp);
		
	
	}
}
