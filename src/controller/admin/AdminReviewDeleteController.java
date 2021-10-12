package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XReview;
import service.face.AdminReviewService;
import service.impl.AdminReviewServiceImpl;

@WebServlet("/admin/review/delete")
public class AdminReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminReviewService adminReviewService = new AdminReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("admin/review/delete [GET]");
		
		XReview reviewno = adminReviewService.getReviewno(req);
		
		adminReviewService.setReviewDelete(reviewno);
		
		resp.sendRedirect("/admin/review/list");
	}
	

}
