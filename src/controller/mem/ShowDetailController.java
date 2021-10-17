package controller.mem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XReview;
import dto.XShow;
import service.face.JjimService;
import service.face.ReviewService;
import service.face.ShowService;
import service.impl.JjimServiceImpl;
import service.impl.ReviewServiceImpl;
import service.impl.ShowServiceImpl;
import util.Paging;

/**
 * Servlet implementation class ShowDetailController
 */
@WebServlet("/show/detail")
public class ShowDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ShowService showService = new ShowServiceImpl();
	private JjimService jjimService = new JjimServiceImpl();
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/show/detail [GET]");
		
		XShow showNo = showService.getShowNo(req);
		XShow showDetail = showService.viewShowInfo(showNo);
		
		String kindName = showService.getkindName(showDetail.getKindNo());
		String genreName = showService.getGenreName(showDetail);
		String hallName = showService.getHallName(showDetail);
		
		Paging paging = reviewService.getPaging(req, 5, showDetail.getShowNo());
		
		boolean isJjim = jjimService.getisJjim((String)req.getSession().getAttribute("memid"), showNo.getShowNo());
		
		double scoreAvg = reviewService.getAvgReviewScoreByShowNo(showNo.getShowNo());
		
		//List<XReview> reviewList = reviewService.getListDateBy(paging);
		
		req.setAttribute("showKindName", kindName);
		req.setAttribute("showGenreName", genreName);
		req.setAttribute("showHallName", hallName);
		req.setAttribute("showDetail", showDetail);
		req.setAttribute("isJjim", isJjim);
		req.setAttribute("showScoreAvg", scoreAvg);
		
		//요청 보내기
		req.getRequestDispatcher("/WEB-INF/views/mem/show/detail.jsp").forward(req, resp);
	}
}
