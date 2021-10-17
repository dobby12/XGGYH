package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XMem;
import dto.XReview;
import service.face.AdminMemberService;
import service.face.AdminReviewService;
import service.face.AdminService;
import service.impl.AdminMemberServiceImpl;
import service.impl.AdminReviewServiceImpl;
import service.impl.AdminServiceImpl;
import util.Paging;

@WebServlet("/admin/mem/review")
public class AdminMemReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminReviewService adminReviewService = new AdminReviewServiceImpl();
	private AdminMemberService adminMemberService = new AdminMemberServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Paging paging = adminReviewService.getPaging(req);
		
		//요청정보로 member객체 받아오기
		XMem reviewMem = adminMemberService.getMemId(req);
		
		//멤버가 작성한 리스트 객체
		List<XReview> memReviewList = adminReviewService.getReviewListByMem(paging, reviewMem);
		
		req.setAttribute("memReviewList", memReviewList);
				
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/mem/review");
		
		req.setAttribute("memid", req.getParameter("memid"));
		
//		System.out.println(memReviewList);
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/mem/review.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");
		
	
	}

}
