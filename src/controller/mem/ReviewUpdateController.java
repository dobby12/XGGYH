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

	//BoardService 객체 생성
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달파라미터 얻기 - boardno
		XReview review_no = reviewService.getReview_no(req);

		//상세보기 결과 조회
		XReview updateReview = reviewService.view(Review_no);
		
		//닉네임 전달
		req.setAttribute("mem_nick", reviewService.getMem_nick(updateReview));
	
		//조회결과 MODEL값 전달
		req.setAttribute("updateReivew", updateReview);

		//첨부파일 정보 VIEW에 전달
		XFile xFile = reviewService.viewFile(updateReview);
		req.setAttribute("xFile", xFile);

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(req, resp);		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		reviewService.update(req);
		
		resp.sendRedirect("/board/list");
		
	}
}
