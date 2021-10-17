package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.XNotice;
import service.face.AdminNoticeService;
import service.face.AdminService;
import service.impl.AdminNoticeServiceImpl;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/notice/delete")
public class AdminNoticeDeleteController extends HttpServlet {
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

		System.out.println("###TEST### AdminNoticeDeleteController Get");
		
		String param = req.getParameter("noticeno");
		int noticeno = 0;
		if(param!=null && !"".equals(param)) {
			noticeno = Integer.parseInt(param);
		} else {
			System.out.println("!!!ERROR!!! noticeno로 전달된 파라미터가 숫자가 아닙니다.");
			return;
		}
				
		adminNoticeService.setNoticeDelete(noticeno);
		
		resp.sendRedirect("/admin/notice/list");
				
	}

}
