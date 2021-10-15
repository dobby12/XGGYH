package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.JjimService;
import service.impl.JjimServiceImpl;

/**
 * Servlet implementation class JjimDeleteController
 */
@WebServlet("/mem/jjim/delete")
public class JjimDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JjimService jjimService = new JjimServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mem/jjim/delete [POST]");
		
		String memId = req.getParameter("memId");
		String showNo = req.getParameter("showNo");
		
		int isDeleteAble = jjimService.setJjimDelete(memId, showNo);
		
		System.out.println("삭제 여부 : " + isDeleteAble);
		
		if(isDeleteAble == 0)
			System.out.println("찜 목록 삭제 실패");
		else
			System.out.println("찜 목록 삭제 성공");
		
		//요건 링크 바꿔야됨
		resp.sendRedirect("/main");
	}
}