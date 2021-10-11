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

	//BoardService 객체 생성
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = reviewService.getPaging(req);
		System.out.println("ReviewListController [GET] - " + paging);
		
		//게시글 전체 조회
		List<XReview> reviewList = reviewService.getList(paging);
		
		//조회결과 MODEL값 전달
		req.setAttribute("reviewList", reviewList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/review/list.jsp").forward(req, resp);		
		
	}
	
}












