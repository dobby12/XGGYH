package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XNotice;
import service.face.AdminNoticeService;
import service.face.AdminService;
import service.impl.AdminNoticeServiceImpl;
import service.impl.AdminServiceImpl;
import util.Paging;

@WebServlet("/admin/notice/list")
public class AdminNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("승인된 관리자 로그인 상태 : "+adminService.authorAdmin((String)req.getSession().getAttribute("adminid")));
		if(!adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {			
			resp.sendRedirect("/admin");
			return;
		}
		System.out.println("###TEST### AdminNoticeListController Get");
		
		Paging paging = adminNoticeService.getPaging(req);
		System.out.println("/admin/notice/list [GET] - " + paging);
		
//		List<XNotice> list = adminNoticeService.getNoticeList();
		List<XNotice> list = adminNoticeService.getNoticeList(paging);
		
		req.setAttribute("noticeList", list);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/notice/list");
		
		req.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(req, resp);
	}
	
}
