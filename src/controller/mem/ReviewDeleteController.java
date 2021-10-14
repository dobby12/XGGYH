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
		
		System.out.println(param + "param");//162확인
		
		int reviewno = 0;
		if(param != null && !"".equals(param)) {
			reviewno = Integer.parseInt(param);
		} else {
			System.out.println("!!!ERROR!!!");
			return;
		}
		
		System.out.println(reviewno + "reviewno");//162확인
		
		XReview review = reviewService.getReviewDetail(reviewno);
		
		System.out.println(review + "review");
		
		HttpSession session = req.getSession();
		
		System.out.println(session + "session");
		
		if(!review.getMemId().equals(session.getAttribute("mem_id"))) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!");
			return;
		}
		
		reviewService.delete(reviewno);
		
		resp.sendRedirect("/review/list");	
	}
}
