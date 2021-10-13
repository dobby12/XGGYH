package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AdminMailService;
import service.impl.AdminMailServiceImpl;

@WebServlet("/admin/mail/mem/write")
public class AdminMailMemWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminMailService adminMailService = new AdminMailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/mem/write [GET]");

		req.setCharacterEncoding("utf-8");

		String memMail = req.getParameter("memmail");

		req.setAttribute("adminMail", "gonggongyeonhee@gmail.com");

		req.setAttribute("memMail", memMail);
		
		System.out.println(memMail);

		req.getRequestDispatcher("/WEB-INF/views/admin/mail/mem/write.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/mem/write [POST]");

		req.setCharacterEncoding("utf-8");

		String memMail = (String)req.getParameter("memmail");
		String mailTitle = req.getParameter("mailtitle");
		String mailContent = req.getParameter("mailcontent");
		
		System.out.println("memmail");

		adminMailService.sendMail(memMail, mailTitle, mailContent);

	}

}
