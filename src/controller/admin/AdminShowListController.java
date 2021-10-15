package controller.admin;

import java.io.IOException;
import java.util.List;

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
import util.Paging;

@WebServlet("/admin/show/list")
public class AdminShowListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminShowService adminShowService = new AdminShowServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/show/list [GET]");

		Paging paging = adminShowService.getPaging(req);
		
		List<XShow> list = adminShowService.getShowList(paging);
		
		req.setAttribute("list", list);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/show/list");
	
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/show/list.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");		
		
	}
	
}
