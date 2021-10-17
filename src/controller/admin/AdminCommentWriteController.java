package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XAsk;
import dto.XComment;
import service.face.AdminAskService;
import service.face.AdminService;
import service.impl.AdminAskServiceImpl;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/comment/write")
public class AdminCommentWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminAskService adminAskService = new AdminAskServiceImpl();
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("승인된 관리자 로그인 상태 : "+adminService.authorAdmin((String)req.getSession().getAttribute("adminid")));
		if(!adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {			
			resp.sendRedirect("/admin/notice/list");
			return;
		}
		
		req.setCharacterEncoding("UTF-8");
		
		XAsk xaskno = adminAskService.getAskNo(req);
		XComment comment = adminAskService.setCommentWrite( req, xaskno );
		
		req.setAttribute("comment", comment);

		resp.sendRedirect(req.getContextPath() + "/admin/ask/detail?askNo=" + req.getParameter("askNo"));

		
	}

}
