package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.face.AdminMailService;
import service.face.AdminService;
import service.impl.AdminMailServiceImpl;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/mail/mem/write")
public class AdminMailMemWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminMailService adminMailService = new AdminMailServiceImpl();
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/mem/write [GET]");

		req.setCharacterEncoding("utf-8");

		String memMail = req.getParameter("memmail");

		req.setAttribute("adminMail", "gonggongyeonhee@gmail.com");

		req.setAttribute("memMail", memMail);
		
		System.out.println(memMail);

		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/mail/mem/write.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/mail/mem/write [POST]");

		req.setCharacterEncoding("utf-8");

		String memMail = (String)req.getParameter("memmail");
		String mailTitle = req.getParameter("mailtitle");
		String mailContent = req.getParameter("mailcontent");
		System.out.println("-------------------------------------------");		
		Gson gson = new Gson();
		
		List<String> list = new ArrayList<>();
		List<String> mailList = gson.fromJson(req.getParameter("result"), list.getClass());
		
//		String[] mailList = jsonString.toArray(new String[jsonString.size()]);
//		
//
//		
//		System.out.println(mailList);//변수에 들어 있는 내용 확인
//		System.out.println(mailList.getClass().getName());//변수타입
//		System.out.println(mailList.get(1));
//		System.out.println(mailList.get(2));
//		System.out.println("-------------------------------------------");
//		System.out.println("memmail");

		adminMailService.sendMail(mailList, mailTitle, mailContent);

	}

}
