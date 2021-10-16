package controller.mem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XShow;
import service.face.JjimService;
import service.impl.JjimServiceImpl;

@WebServlet("/mycalendar")
public class MyCalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JjimService jjimService = new JjimServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mycalendar [GET]");
		
		String memid = (String)req.getSession().getAttribute("memid");
		
		List<XShow> showList = jjimService.getShowNoByMemId(memid);
		
		System.out.println(showList);
		
		req.setAttribute("memid", memid);

		req.setAttribute("showList", showList);
		
		req.getRequestDispatcher("/WEB-INF/views/mem/mycalendar.jsp").forward(req, resp);
		
		
		
		
		
	}
	
	
}
