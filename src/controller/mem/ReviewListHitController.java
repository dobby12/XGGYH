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

@WebServlet("/review/listhit")
public class ReviewListHitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = reviewService.getPaging(req);
		System.out.println("ReviewListController [GET] - " + paging);
		
		List<XReview> reviewList = reviewService.getListhit(paging);
		
		req.setAttribute("reviewList", reviewList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/review/listhit");
		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/listhit.jsp").forward(req, resp);		
		
	}
	
}