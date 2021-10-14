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
import service.impl.AdminNoticeServiceImpl;
import util.Paging;

@WebServlet("/admin/notice/list")
public class AdminNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### AdminNoticeListController Get");
		
		Paging paging = adminNoticeService.getPaging(req);
		System.out.println("/admin/notice/list [GET] - " + paging);
		
		List<XNotice> list = adminNoticeService.getNoticeList();

		req.setAttribute("noticeList", list);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("linkUrl", "/admin/notice/list");
		
		req.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(req, resp);
	}
	
}
