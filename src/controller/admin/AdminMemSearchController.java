package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XMem;
import service.face.AdminMemberService;
import service.face.AdminService;
import service.impl.AdminMemberServiceImpl;
import service.impl.AdminServiceImpl;
import util.Paging;

@WebServlet("/admin/mem/search")
public class AdminMemSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMemberService adminMemberService = new AdminMemberServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		Paging paging = adminMemberService.getParameterPaging(req);
		System.out.println("AdminMemberListController [GET] - " + paging);

		//전달 파라미터 searchtype, keyword를 통해서 searchMember객체를 반환
		List<XMem> searchMemList = adminMemberService.searchMemList(req, paging);
				
		req.setAttribute("searchMemList", searchMemList);
		
		req.setAttribute("paging", paging);
		
		req.setCharacterEncoding("UTF-8");
		
		req.setAttribute("linkUrl", "/admin/mem/search?searchtype=" + req.getParameter("searchtype") + "&keyword=" + req.getParameter("keyword"));
		
//		System.out.println("linkUrl : admin/mem/search?searchtype=" + req.getParameter("searchtype") + "&keyword=" + req.getParameter("keyword"));
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/mem/search.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");
		
		
	
	}
}
