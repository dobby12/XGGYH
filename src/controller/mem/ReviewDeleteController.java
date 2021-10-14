package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.XReview;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

@WebServlet("/review/delete")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review/delete [GET]");
		
		String param = req.getParameter("reviewno");
		
		int reviewno = 0;
		if(param != null && !"".equals(param)) {
			reviewno = Integer.parseInt(param);
		} else {
			System.out.println("!!!ERROR!!!");
			return;
		}
		
		XReview review = reviewService.getReviewDetail(reviewno);
		HttpSession session = req.getSession();
		if(!review.getMemId().equals(session.getAttribute("memid"))) {
			System.out.println("!!!!!!!!! ERROR ERROR ERROR !!!!!!!!!!");
			return;
		}
		
		reviewService.delete(reviewno);
		
		resp.sendRedirect("/review/list");	
	}
}
