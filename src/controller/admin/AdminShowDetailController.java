package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XFile;
import dto.XShow;
import service.face.AdminService;
import service.face.AdminShowService;
import service.impl.AdminServiceImpl;
import service.impl.AdminShowServiceImpl;

@WebServlet("/admin/show/detail")
public class AdminShowDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminShowService adminShowService = new AdminShowServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		XShow showno = adminShowService.getShowno(req);
		
		XShow viewShow = adminShowService.getShowDetail(showno);
		
		req.setAttribute("viewShow", viewShow);
		
		//장르 전달
		req.setAttribute("showGenre", adminShowService.getGenre(viewShow));

		//공연 종류 전달
		req.setAttribute("showKind", adminShowService.getKind(viewShow));
		
		//공연장 이름 전달
		req.setAttribute("showHall", adminShowService.getHallname(viewShow));
		
		XFile showFile = adminShowService.getFile(viewShow);
		
		//첨부파일 정보 전달
		req.setAttribute("showFile", showFile);
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/show/detail.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");		
		
		
		
		
	}
	
}
