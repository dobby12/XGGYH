package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AdminMailService;
import service.impl.AdminMailServiceImpl;

@WebServlet("/admin/mail/write")
public class AdminMailWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMailService adminMailService = new AdminMailServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/write [GET]");
		
		req.setAttribute("adminMail", "gonggongyeonhee@gmail.com");
		
		req.getRequestDispatcher("/WEB-INF/views/admin/mail/write.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/write [POST]");
		
		req.setCharacterEncoding("utf-8");
		
		String memMail = req.getParameter("memMail");
		String mailTitle = req.getParameter("mailTitle");
		String mailContent = req.getParameter("mailContent");
		
		adminMailService.sendMail(memMail, mailTitle, mailContent);
		
	
	}

}
