package controller.mem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XShow;
import service.face.ShowService;
import service.impl.ShowServiceImpl;

/**
 * Servlet implementation class ShowDetailController
 */
@WebServlet("/show/detail")
public class ShowDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ShowService showService = new ShowServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/show/detail [GET]");
		
		XShow showNo = showService.getShowNo(req);
		XShow showDetail = showService.viewShowInfo(showNo);
		
		String kindName = showService.getKindName(showDetail);
		String genreName = showService.getGenreName(showDetail);
		String hallName = showService.getHallName(showDetail);
		
		req.setAttribute("showKindName", kindName);
		req.setAttribute("showGenreName", genreName);
		req.setAttribute("showHallName", hallName);
		req.setAttribute("showDetail", showDetail);
		
		//요청 보내기
		req.getRequestDispatcher("/WEB-INF/views/mem/show/detail.jsp").forward(req, resp);
	}
}
