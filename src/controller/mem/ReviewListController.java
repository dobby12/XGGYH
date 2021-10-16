package controller.mem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XReview;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;
import util.Paging;

@WebServlet("/review/list")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = reviewService.getPaging(req);
		System.out.println("ReviewListController [GET] - " + paging);
		
		List<XReview> reviewList = reviewService.getList(paging);
		

		System.out.println("reviewList : " + reviewList);
		XReview reviewNo = reviewService.getReviewNo(req);
		XReview viewReview = reviewService.view(reviewNo);
		System.out.println("reviewNo : " + reviewNo);
		System.out.println("viewReview : " + viewReview);
		req.setAttribute("viewReview", viewReview);
		req.setAttribute("memNick", reviewService.getMemNick(viewReview));
		req.setAttribute("showTitle", reviewService.getShowTitle(viewReview));

		
		
		
		
		req.setAttribute("reviewList", reviewList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/review/list");
		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/list.jsp").forward(req, resp);		
		
	}
	
}