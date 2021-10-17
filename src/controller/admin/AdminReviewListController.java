package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XReview;
import service.face.AdminReviewService;
import service.face.AdminService;
import service.face.ReviewService;
import service.impl.AdminReviewServiceImpl;
import service.impl.AdminServiceImpl;
import service.impl.ReviewServiceImpl;
import util.Paging;


@WebServlet("/admin/review/list")
public class AdminReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminReviewService adminReviewService = new AdminReviewServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	private ReviewService reviewService = new ReviewServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
//			//현재 로그인 되어 있는 관리자 아이디 adminid이 DB에 저장된 admin_id이며, admin_authority값이 y인 경우가 맞다면
//			req.getRequestDispatcher("/WEB-INF/views/admin/review/list.jsp").forward(req, resp);
//			
//			return;
//		} 
//		
//		resp.sendRedirect("/admin");
		
		
		//요청 파라미터 전달 -> 페이징 객체 생성
		Paging paging = adminReviewService.getPaging(req);
		System.out.println("AdminReviewListController [GET] - " + paging);	
		
		//게시글 전체 조회
		List<XReview> reviewList = adminReviewService.getReviewList(paging);
		
		ArrayList<String> showTitle = new ArrayList<>();
		for (int i = 0; i < reviewList.size(); i++) {
			showTitle.add(reviewService.getShowTitle(reviewList.get(i)));
		}
		
		req.setAttribute("showTitle", showTitle);
		
		req.setAttribute("reviewList", reviewList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/review/list");
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/review/list.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");
				
	}
	
	
}
