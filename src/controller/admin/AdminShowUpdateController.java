package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.XAdmin;
import dto.XFile;
import dto.XShow;
import service.face.AdminService;
import service.face.AdminShowService;
import service.impl.AdminServiceImpl;
import service.impl.AdminShowServiceImpl;

@WebServlet("/admin/show/update")
public class AdminShowUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminShowService adminShowService = new AdminShowServiceImpl();
	AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		XShow xshowno = adminShowService.getShowno(req);
		XShow xshow = adminShowService.getShowDetail(xshowno);
		System.out.println(xshow);
		XFile file = adminShowService.getFile(xshow);
		
		req.setAttribute("xshow", xshow);
		req.setAttribute("file", file);
		
		XAdmin admin = adminService.getLoginAdmin(req);
		HttpSession session = req.getSession();
		
		if(adminService.loginAdmin(admin)) {
			session.setAttribute("adminlogin", true);
			session.setAttribute("adminid", adminService.getAdmin(admin).getAdminId());
			session.setAttribute("adminname", adminService.getAdmin(admin).getAdminName());
			session.setAttribute("adminauthority", adminService.getAdmin(admin).getAdminAuthority());
		} else {
			session.setAttribute("loginfail", true);
		}
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/show/update.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		adminShowService.setShowUpdate(req);
		
		resp.sendRedirect("/admin/show/list");
	
	}
}
