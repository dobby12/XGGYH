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
 * Servlet implementation class ShowListController
 */
@WebServlet("/show/list")
public class ShowListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ShowService showService = new ShowServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/show/list : Get");
		
		for(XShow e : showService.getShowList())
			System.out.println(e);
		
		//요청 보내기
		req.getRequestDispatcher("/WEB-INF/views/mem/show/list.jsp").forward(req, resp);
	}
}