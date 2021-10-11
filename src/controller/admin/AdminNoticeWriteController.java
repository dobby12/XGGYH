package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AdminNoticeService;
import service.impl.AdminNoticeServiceImpl;

@WebServlet("/admin/notice/delete")
public class AdminNoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### AdminNoticeWriteController Get");

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### AdminNoticeWriteController Post");
	}
	
}
