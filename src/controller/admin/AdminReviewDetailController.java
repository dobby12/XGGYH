package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XFile;
import dto.XReview;
import service.face.AdminReviewService;
import service.face.AdminService;
import service.impl.AdminReviewServiceImpl;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/review/detail")
public class AdminReviewDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminReviewService adminReviewService = new AdminReviewServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - reviewno
		XReview reviewno = adminReviewService.getReviewno(req);
		
		//상세보기 결과 조회
		XReview viewReview = adminReviewService.getReviewDetail(reviewno);
		
		//조회결과 전달
		req.setAttribute("viewReview", viewReview);
	
		//닉네임 전달
		req.setAttribute("memNick", adminReviewService.getNick(viewReview));
		
		//공연 제목 전달
		req.setAttribute("showTitle", adminReviewService.getTitle(viewReview));
		
		//첨부파일 정보 조회
		XFile reviewFile = adminReviewService.getFile(viewReview);
		
		//첨부파일 정보 model값 전달
		req.setAttribute("reviewFile", reviewFile);
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/review/detail.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");
		
	}


}
