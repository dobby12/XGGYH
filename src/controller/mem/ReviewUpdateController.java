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

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review/update [GET]");
		
		String param = req.getParameter("reviewno");
		int reviewno = 0;
		if(param!=null && !"".equals(param)) {
			reviewno = Integer.parseInt(param);
		} else {
			System.out.println("!!!ERROR!!! reviewno로 전달된 파라미터가 숫자가 아닙니다.");
		}
		
		//전달파라미터
		XReview reviewNo = reviewService.getReviewNo(req);
		
		//상세보기
		XReview updateReview = reviewService.view(reviewNo);

		//닉네임
		req.setAttribute("memNick", reviewService.getMemNick(updateReview));
		
		//공연제목
		req.setAttribute("showTitle", reviewService.getShowTitle(updateReview));
		
		//조회결과값 전달
		req.setAttribute("updateReview", updateReview);

		//첨부파일 전달
		XFile xFile = reviewService.getFile(reviewno);
		req.setAttribute("xFile", xFile);

		req.getRequestDispatcher("/WEB-INF/views/mem/review/update.jsp").forward(req, resp);		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review/update [POST]");
		
		reviewService.update(req);
		
		resp.sendRedirect("/review/list");
		
	}
}
