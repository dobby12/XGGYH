package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

@WebServlet("/review/write")
public class ReviewWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review/write [GET]");
		
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		req.setAttribute("showTitle", req.getParameter("showTitle"));
		//req.setAttribute("showTitle", showno를 title로 바꾸는 메소드);
		
//		<button  style="margin: auto 0"onclick="location.href='<%=request.getContextPath()%>/review/write?showNo=${showDetail.showNo}';">리뷰 작성</button>

		
		req.getRequestDispatcher("/WEB-INF/views/mem/review/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review/write [POST]");

		reviewService.write(req);
		
		resp.sendRedirect("/review/list");
	}
}
