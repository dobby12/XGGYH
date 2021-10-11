package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XHall;
import service.face.HallService;
import service.impl.HallServiceImpl;

/**
 * Servlet implementation class HallController
 */
@WebServlet("/hall/detail")
public class HallController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HallService hallService = new HallServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("/hall/detail [GET]");
			
			//공연장의 번호를 req를 통해 받아옴
			int hallNo = hallService.getHallNo(req);
			XHall hallInfo = hallService.getHallInfo(hallNo);
			
			req.setAttribute("hallInfo", hallInfo);
			req.getRequestDispatcher("/WEB-INF/views/mem/hall/detail.jsp").forward(req, resp);
		}
}
