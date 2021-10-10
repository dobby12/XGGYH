package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XReview;
import service.face.AdminReviewService;
import service.impl.AdminReviewServiceImpl;

@WebServlet("/admin/review/detail")
public class AdminReviewDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminReviewService adminReviewService = new AdminReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - reviewno
		XReview reviewno = adminReviewService.getReviewno(req);
		
		//상세보기 결과 조회
		XReview viewReview = adminReviewService.getReviewDetail(reviewno);
		
		//조회결과 전달
		req.setAttribute("viewReview", viewReview);
	
		//닉네임 전달
		req.setAttribute("memnick", adminReviewService.getNick(viewReview));
		
		//공연 제목 전달
		req.setAttribute("showtitle", adminReviewService.getTitle(viewReview));
		
		req.getRequestDispatcher("/WEB-INF/views/admin/review/detail.jsp").forward(req, resp);
		
	}


}
