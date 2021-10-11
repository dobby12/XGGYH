package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XFile;
import dto.XReview;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

@WebServlet("/review/update")
public class ReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		XReview reviewNo = reviewService.getReviewNo(req);

		XReview updateReview = reviewService.view(reviewNo);
		
		req.setAttribute("mem_nick", reviewService.getMemNick(updateReview));
	
		req.setAttribute("updateReivew", updateReview);

		XFile xFile = reviewService.viewFile(updateReview);
		req.setAttribute("xFile", xFile);

		req.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(req, resp);		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		reviewService.update(req);
		
		resp.sendRedirect("/board/list");
		
	}
}
