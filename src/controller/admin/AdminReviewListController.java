package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XReview;
import service.face.AdminReviewService;
import service.impl.AdminReviewServiceImpl;
import util.Paging;


@WebServlet("/admin/review/list")
public class AdminReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminReviewService adminReviewService = new AdminReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 파라미터 전달 -> 페이징 객체 생성
		Paging paging = adminReviewService.getPaging(req);
		System.out.println("AdminReviewListController [GET] - " + paging);	
		
		//게시글 전체 조회
		List<XReview> reviewList = adminReviewService.getReviewList(paging);
		
		req.setAttribute("reviewList", reviewList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/review/list");
		
		req.getRequestDispatcher("/WEB-INF/views/admin/review/list.jsp").forward(req, resp);
				
	}
	
	
}
