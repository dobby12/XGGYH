package controller.admin;

import java.io.IOException;

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

@WebServlet("/admin/mem/delete")
public class AdminMemDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminMemberService adminMemberService = new AdminMemberServiceImpl();
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mem/delete [GET]");
		
		XMem memid = adminMemberService.getMemId(req);
		
		adminMemberService.setMemDelete(memid);
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			resp.sendRedirect("/admin/mem/list");
			return;
		}
		resp.sendRedirect("/admin");
		
	}
	

}
