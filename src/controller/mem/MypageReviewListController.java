package controller.mem;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/mypage/myreview")
public class MypageReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/myreview [GET]");
		
		//로그인한 회원아이디
		String memid = (String)req.getSession().getAttribute("memid");
		
		Paging paging = reviewService.getPagingByMemId(req, memid);
		
		//로그인한 회원아이디가 작성한 리스트 객체
		List<XReview> memidReviewList = reviewService.getReviewListByMemid(paging, memid);
		
		ArrayList<String> showTitle = new ArrayList<>();
		for (int i = 0; i < memidReviewList.size(); i++) {
			showTitle.add(reviewService.getShowTitle(memidReviewList.get(i)));
		}
		
		req.setAttribute("showTitle", showTitle);
		
		req.setAttribute("memidReviewList", memidReviewList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/mypage/myreview");
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/myreview.jsp").forward(req, resp);
		
	}
}
