package controller.admin;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AdminNoticeService;
import service.face.AdminService;
import service.impl.AdminNoticeServiceImpl;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/notice/write")
public class AdminNoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### AdminNoticeWriteController Get");

		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			//현재 로그인 되어 있는 관리자 아이디 adminid이 DB에 저장된 admin_id이며, admin_authority값이 y인 경우가 맞다면
			req.getRequestDispatcher("/WEB-INF/views/admin/notice/write.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/XGGYH/admin");	//@@@보낼 url 어디?
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### AdminNoticeWriteController Post");

		adminNoticeService.setNotice(req);
		
		resp.sendRedirect("/XGGYH/admin/notice/list");	//@@@url
	}
	
}
