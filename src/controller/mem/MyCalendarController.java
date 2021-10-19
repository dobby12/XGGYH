package controller.mem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XShow;
import service.face.JjimService;
import service.impl.JjimServiceImpl;
import util.Paging;

@WebServlet("/mycalendar")
public class MyCalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JjimService jjimService = new JjimServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mycalendar [GET]");
		
		String memid = (String)req.getSession().getAttribute("memid");
		
		Paging paging = jjimService.getPaging(req, memid);

		
		List<XShow> showList = jjimService.getShowNoByMemId(paging, memid);
		
		System.out.println(showList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("memid", memid);

		req.setAttribute("showList", showList);

		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('로그인 후 이용 가능한 서비스입니다. 로그인 해주세요.');"
							+ "location.href='/login';</script>");
			writer.close();
			
			return;
		}
		req.getRequestDispatcher("/WEB-INF/views/mem/mycalendar/mycalendar.jsp").forward(req, resp);

	}	
}
