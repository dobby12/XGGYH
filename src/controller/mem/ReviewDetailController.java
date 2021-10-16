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

		//전달파라미터
		XReview reviewNo = reviewService.getReviewNo(req);

		//상세보기결과
		XReview viewReview = reviewService.view(reviewNo);
		
		//조회결과값 전달
		req.setAttribute("viewReview", viewReview);
		
		//** 평점 평균값 구하기 **
		int showno = viewReview.getShowNo();
		req.setAttribute("AvgReview", reviewService.getAvgReviewScoreByShowNo(showno));
		
		//닉네임 전달
		req.setAttribute("memNick", reviewService.getMemNick(viewReview));
		
		//공연 제목 전달
		req.setAttribute("showTitle", reviewService.getShowTitle(viewReview));
		
		//첨부파일 정보 조회
		XFile reviewFile = reviewService.viewFile(viewReview);
		
		//첨부파일 정보값 전달
		req.setAttribute("reviewFile", reviewFile);
		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/detail.jsp").forward(req, resp);		
	}
}
