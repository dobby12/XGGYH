package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XShow;
import service.face.AdminService;
import service.face.AdminShowService;
import service.impl.AdminServiceImpl;
import service.impl.AdminShowServiceImpl;
import util.Paging;

@WebServlet("/admin/show/search")
public class AdminShowSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminShowService adminShowService = new AdminShowServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/show/search [GET]");
		
		Paging paging = adminShowService.getParameterPaging(req);
		System.out.println("AdminShowListController [GET] - " + paging);

		//전달 파라미터 searchtype, keyword를 통해서 searchMember객체를 반환
		List<XShow> searchShowList = adminShowService.searchShowList(req, paging);
				
		req.setAttribute("searchShowList", searchShowList);
		
		req.setAttribute("paging", paging);
		
		req.setCharacterEncoding("UTF-8");
		
		req.setAttribute("linkUrl", "/admin/show/search?keyword=" + req.getParameter("keyword"));
		
		req.setAttribute("keyword", req.getParameter("keyword"));
		
//		System.out.println("linkUrl : admin/show/search?searchtype=" + req.getParameter("searchtype") + "&keyword=" + req.getParameter("keyword"));
		
		if(adminService.authorAdmin((String)req.getSession().getAttribute("adminid"))) {
			req.getRequestDispatcher("/WEB-INF/views/admin/show/search.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/admin");
	
	}
	

}
