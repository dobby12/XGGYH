package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XFile;
import dto.XNotice;
import service.face.AdminNoticeService;
import service.impl.AdminNoticeServiceImpl;

@WebServlet("/admin/notice/update")
public class AdminNoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### AdminNoticeUpdateController Get");

		String param = req.getParameter("noticeno");
		int noticeno = 0;
		if(param!=null && !"".equals(param)) {
			noticeno = Integer.parseInt(param);
		} else {
			System.out.println("!!!ERROR!!! noticeno로 전달된 파라미터가 숫자가 아닙니다.");
		}
		XNotice notice = adminNoticeService.getNoticeDetail(noticeno);
		req.setAttribute("notice", notice);
		
		XFile file = adminNoticeService.getFile(noticeno);
		req.setAttribute("file", file);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/notice/update.jsp").forward(req, resp);
		
		
		
//		System.out.println("noticeno : " + req.getParameter("noticeno"));
//		System.out.println("adminid : " + req.getSession().getAttribute("adminid"));
//
//		adminNoticeService.setNoticeUpdate(req);
//		
//		String param = req.getParameter("noticeno");
//		int noticeno = 0;
//		if(param!=null && !"".equals(param)) {
//			noticeno = Integer.parseInt(param);
//		} else {
//			System.out.println("!!!ERROR!!! noticeno로 전달된 파라미터가 숫자가 아닙니다.");
//		}
//		XNotice notice = adminNoticeService.getNoticeDetail(noticeno);
//		req.setAttribute("notice", notice);
//		
//		XFile file = adminNoticeService.getFile(noticeno);
//		req.setAttribute("file", file);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### AdminNoticeUpdateController Post");

		adminNoticeService.setNoticeUpdate(req);
	
		resp.sendRedirect("/admin/notice/list");
	}
	
}
