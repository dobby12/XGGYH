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
import service.face.AdminService;
import service.impl.AdminMemberServiceImpl;
import service.impl.AdminServiceImpl;
import util.Paging;

@WebServlet("/admin/mem/list")
public class AdminMemListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMemberService adminMemberService = new AdminMemberServiceImpl();
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = adminMemberService.getPaging(req);
		System.out.println("AdminMemberListController [GET] - " + paging);
		
		//회원 정보 전체 조회
		List<XMem> memList = adminMemberService.getMemList(paging);
		
		req.setAttribute("memList", memList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/mem/list");
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/mem/list.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");		
	}
	

}
