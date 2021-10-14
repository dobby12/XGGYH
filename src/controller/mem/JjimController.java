package controller.mem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XJjim;
import service.face.JjimService;
import service.impl.JjimServiceImpl;

/**
 * Servlet implementation class JjimController
 */
@WebServlet("/mem/jjim")
public class JjimController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JjimService jjimService = new JjimServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mem/jjim [POST]");
		
		XJjim jjim = jjimService.getJjimInfo(req);
		
		if(req.getSession().getAttribute("login") == null || !(boolean)req.getSession().getAttribute("login"))
		{
			System.out.println("로그인 안됨");
			return;
		}
		else {
			System.out.println(req.getParameter("memId"));
			System.out.println(req.getParameter("showNo"));
			System.out.println("로그인 정보 가져오기 성공");
			
			int insertAble = jjimService.setJjim(jjim);
		}
	}
}
