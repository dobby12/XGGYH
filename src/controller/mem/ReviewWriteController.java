package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XShow;
import service.face.ReviewService;
import service.face.ShowService;
import service.impl.ReviewServiceImpl;
import service.impl.ShowServiceImpl;

@WebServlet("/review/write")
public class ReviewWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	private ShowService showService = new ShowServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review/write [GET]");
		
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		XShow showNo = showService.getShowNo(req);
		
		XShow showDetail = showService.viewShowInfo(showNo);

		req.setAttribute("showDetail", showDetail);

		req.setAttribute("showTitle", req.getParameter("showTitle"));
		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review/write [POST]");

		reviewService.write(req);
		
		resp.sendRedirect("/review/list");
	}
}
