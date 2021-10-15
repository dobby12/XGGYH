package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AdminMailService;
import service.face.AdminService;
import service.impl.AdminMailServiceImpl;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/mail/write")
public class AdminMailWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMailService adminMailService = new AdminMailServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/write [GET]");
		
		req.setAttribute("adminMail", "gonggongyeonhee@gmail.com");
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/mail/write.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/write [POST]");
		
		req.setCharacterEncoding("utf-8");
		
		String memMail = req.getParameter("memmail");
		String mailTitle = req.getParameter("mailtitle");
		String mailContent = req.getParameter("mailcontent");
		
		adminMailService.sendMail(memMail, mailTitle, mailContent);
		
	
	}

}
