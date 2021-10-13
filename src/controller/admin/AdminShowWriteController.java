package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.XAdmin;
import service.face.AdminService;
import service.face.AdminShowService;
import service.impl.AdminServiceImpl;
import service.impl.AdminShowServiceImpl;

@WebServlet("/admin/show/write")
public class AdminShowWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminShowService adminShowService = new AdminShowServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {

			req.getRequestDispatcher("/WEB-INF/views/admin/show/write.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");

		adminShowService.setShow( req );
//		
//		System.out.println("showContent: " + req.getParameter("showContent"));
//		System.out.println("showStart : " + req.getParameter("showStart"));
//		System.out.println("showEnd : " + req.getParameter("showEnd"));
		resp.sendRedirect("/admin/show/list");
	}

}
