package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.XAdmin;
import dto.XAsk;
import service.face.AdminAskService;
import service.face.AdminService;
import service.impl.AdminAskServiceImpl;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/ask/detail")
public class AdminAskDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminAskService adminAskService = new AdminAskServiceImpl();
	AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		XAsk xaskno = adminAskService.getAskNo(req);
		
		XAsk xask = adminAskService.getAskDetail(xaskno);
	
		XAdmin admin = adminService.getLoginAdmin(req);
		HttpSession session = req.getSession();
		
		req.setAttribute("xask", xask);
		req.setAttribute("xaskno", xaskno);
		req.setAttribute("nick", adminAskService.getNick(xask));
		
		if(adminService.loginAdmin(admin)) {
			session.setAttribute("login", true);
			session.setAttribute("adminid", adminService.getAdmin(admin).getAdminId());
			session.setAttribute("adminname", adminService.getAdmin(admin).getAdminName());
			session.setAttribute("adminauthority", adminService.getAdmin(admin).getAdminAuthority());
		} else {
			session.setAttribute("loginfail", true);
		}
		
		req.getRequestDispatcher("/WEB-INF/views/admin/ask/detail.jsp").forward(req, resp);
		
	}

}
