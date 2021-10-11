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

@WebServlet("/review/detail")
public class ReviewDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		XReview reviewNo = reviewService.getReviewNo(req);

		
		XReview viewReview = reviewService.view(reviewNo);
		
		req.setAttribute("viewReview", viewReview);

		req.setAttribute("memNick", reviewService.getMemNick(viewReview));
		
		req.setAttribute("showTitle", reviewService.getShowTitle(viewReview));
		
		XFile reviewFile = reviewService.viewFile(viewReview);
		
		req.setAttribute("reviewFile", reviewFile);
		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/detail.jsp").forward(req, resp);		
	}
}
