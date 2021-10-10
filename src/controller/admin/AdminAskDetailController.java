package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XAsk;
import service.face.AdminAskService;
import service.impl.AdminAskServiceImpl;

@WebServlet("/admin/ask/detail")
public class AdminAskDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminAskService adminAskService = new AdminAskServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		XAsk xaskno = adminAskService.getAsk_no(req);
		
		XAsk xask = adminAskService.getAskDetail(xaskno);
		
		req.setAttribute("xask", xask);
		req.setAttribute("xaskno", xaskno);
		req.setAttribute("nick", adminAskService.getNick(xask));
		
		req.getRequestDispatcher("/WEB-INF/views/admin/ask/detail.jsp").forward(req, resp);
		
	}

}
