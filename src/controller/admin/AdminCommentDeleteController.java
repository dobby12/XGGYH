package controller.admin;

import java.io.IOException;

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


@WebServlet("/admin/comment/delete")
public class AdminCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminAskService adminAskService = new AdminAskServiceImpl();
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("승인된 관리자 로그인 상태 : "+adminService.authorAdmin((String)req.getSession().getAttribute("adminid")));
		if(!adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {			
			resp.sendRedirect("/admin");
			return;
		}
		
		System.out.println("/admin/comment/delete [GET]");
		System.out.println(req.getParameter("askNo"));
		
//		전달 파라미터 얻기 - askno
		XAsk xaskno = adminAskService.getAskNo(req);
		
		adminAskService.deleteComment(xaskno);
		
		resp.sendRedirect(req.getContextPath() + "/admin/ask/detail?askNo=" + req.getParameter("askNo"));
	}
}
