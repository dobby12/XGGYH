package controller.mem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JjimController
 */
@WebServlet("/mem/jjim")
public class JjimController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mem/jjim [POST]");
		
		if(req.getSession().getAttribute("login") == null)
		{
			System.out.println("로그인 안됨");	
		}
		else {
			System.out.println(req.getSession().getAttribute("memid"));
			System.out.println(req.getSession().getAttribute("memnick"));
			System.out.println("로그인 정보 가져오기 성공");
		}
	}
}
