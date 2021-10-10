package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XAsk;
import service.face.AdminAskService;
import service.impl.AdminAskServiceImpl;
import util.Paging;

@WebServlet("/admin/ask/list")
public class AdminAskListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminAskService adminAskService = new AdminAskServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/list [GET]");
		
		Paging paging = adminAskService.getPaging(req);
		
		List<XAsk> list = adminAskService.getAskList(paging);
		
		req.setAttribute("list", list);
		
		req.setAttribute("paging", paging);
	
		req.getRequestDispatcher("/WEB-INF/views/admin/ask/list.jsp").forward(req, resp);
		
		
	}

}
