package controller.mem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XReview;
import dto.XShow;
import service.face.ReviewService;
import service.face.ShowService;
import service.impl.ReviewServiceImpl;
import service.impl.ShowServiceImpl;
import util.Paging;

@WebServlet("/review/list")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	private ShowService showService = new ShowServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = reviewService.getPaging(req);
		System.out.println("ReviewListController [GET] - " + paging);
		
		List<XReview> reviewList = reviewService.getList(paging);
		
		
		
		



		
			
		
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("paging", paging);
		req.setAttribute("linkUrl", "/review/list");
		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/list.jsp").forward(req, resp);		
		
	}
	
}












