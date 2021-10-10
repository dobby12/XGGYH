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
import service.impl.ReviewSerivceImpl;
import util.Paging;

@WebServlet("/review/list")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewSerivceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review [GET]");
		
		//Paging객체생성
		Paging paging = reviewService.getPaging(req);
		System.out.println("ReviewListController [GET] - " + paging);
		
		//게시글조회
		List<XReview> reviewList = reviewService.getList(paging);
		
		//조회결과전달
		req.setAttribute("reviewList", reviewList);
		
		//페이징전달
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/list.jsp").forward(req, resp);
		
	}

}
