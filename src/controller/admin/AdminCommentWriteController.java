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
import service.impl.AdminAskServiceImpl;

@WebServlet("/admin/comment/write")
public class AdminCommentWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminAskService adminAskService = new AdminAskServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		XAsk xaskno = adminAskService.getAskNo(req);
		XComment comment = adminAskService.setCommentWrite( req, xaskno );
		
		req.setAttribute("comment", comment);

		resp.sendRedirect(req.getContextPath() + "/admin/ask/detail?askNo=" + req.getParameter("askNo"));

		
	}

}
