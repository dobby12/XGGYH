package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XAsk;
import service.face.AdminAskService;
import service.face.AdminService;
import service.impl.AdminAskServiceImpl;
import service.impl.AdminServiceImpl;
import util.Paging;

@WebServlet("/admin/ask/list")
public class AdminAskListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminAskService adminAskService = new AdminAskServiceImpl();
	AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/ask/list [GET]");
		
		
		Paging paging = adminAskService.getPaging(req);
		
		List<XAsk> list = adminAskService.getAskList(paging);
		
		req.setAttribute("list", list);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/ask/list");
	
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			//현재 로그인 되어 있는 관리자 아이디 adminid이 DB에 저장된 admin_id이며, admin_authority값이 y인 경우가 맞다면
			req.getRequestDispatcher("/WEB-INF/views/admin/ask/list.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");	//@@@보낼 url 어디?
		
		
	}

}
