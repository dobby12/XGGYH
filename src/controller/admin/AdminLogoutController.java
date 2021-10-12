package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/logout")
public class AdminLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### AdminLogoutController doGet()"); 
		HttpSession session = req.getSession();
		session.invalidate();
		resp.sendRedirect("/admin");	//로그아웃 시 메인페이지로 이동	@@@수정 꼭 
	}
	
}
