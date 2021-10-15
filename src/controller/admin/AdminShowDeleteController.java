package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XShow;
import service.face.AdminService;
import service.face.AdminShowService;
import service.impl.AdminServiceImpl;
import service.impl.AdminShowServiceImpl;

@WebServlet("/admin/show/delete")
public class AdminShowDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminShowService adminShowService = new AdminShowServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/show/delete [GET]");
		
		XShow showno = adminShowService.getShowno(req);
		
		adminShowService.setShowDelete(showno);
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/show/delete.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");

	}
	
	
}
