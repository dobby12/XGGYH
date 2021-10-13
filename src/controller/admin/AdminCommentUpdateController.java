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
import dto.XComment;
import service.face.AdminAskService;
import service.face.AdminService;
import service.impl.AdminAskServiceImpl;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/comment/update")
public class AdminCommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminAskService adminAskService = new AdminAskServiceImpl();
	AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		XAsk xaskno = adminAskService.getAskNo(req);
		
		XAsk xask = adminAskService.getAskDetail(xaskno);
//		System.out.println(xask);

		XComment xcomment = adminAskService.getComment(xaskno);
//		System.out.println(xcomment);

		XAdmin admin = adminService.getLoginAdmin(req);
		HttpSession session = req.getSession();
		
		req.setAttribute("xask", xask);
		req.setAttribute("xaskno", xaskno);
		req.setAttribute("nick", adminAskService.getNick(xask));
		req.setAttribute("xcomment", xcomment);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/ask/updateComment.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		int commentno = adminAskService.getCommentNo(req);
		
		XComment comment = adminAskService.setCommentUpdate( req, commentno );
		
		req.setAttribute("comment", comment);

		resp.sendRedirect(req.getContextPath() + "/admin/ask/detail?askNo=" + req.getParameter("askNo"));
		
	}

}
