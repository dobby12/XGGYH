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

@WebServlet("/review/search")
public class ReviewSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		Paging paging = reviewService.getParameterPaging(req);
		System.out.println("ReviewSearchController [GET] - " + paging);

		List<XReview> searchReviewList = reviewService.searchReviewList(req, paging);
				
		req.setAttribute("searchReviewList", searchReviewList);
		
		req.setAttribute("paging", paging);
		
		req.setCharacterEncoding("UTF-8");
		
		req.setAttribute("linkUrl", "/review/search?searchtype=" + req.getParameter("searchtype") + "&keyword=" + req.getParameter("keyword"));
		
		req.setAttribute("searchtype", req.getParameter("searchtype"));
		req.setAttribute("keyword", req.getParameter("keyword"));
		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/search.jsp").forward(req, resp);
		
		req.setAttribute("searchtype", req.getParameter("searchtype"));
		req.setAttribute("keyword", req.getParameter("keyword"));
	
	}
}
