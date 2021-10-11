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

	//BoardService 객체 생성
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		XReview review_no = reviewService.getReview_no(req);

		
		XReview viewReview = reviewService.view(review_no);
		
		req.setAttribute("viewReview", viewReview);

		
		req.setAttribute("mem_nick", reviewService.getMem_nick(viewReview));
		
		
		XFile xFile = reviewService.viewFile(viewReview);
		
		req.setAttribute("xFile", xFile);
		
		
		req.getRequestDispatcher("/WEB-INF/views/review/detail.jsp").forward(req, resp);		
	}
	
}
