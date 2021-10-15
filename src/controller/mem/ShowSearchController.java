package controller.mem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XShow;
import service.face.ShowService;
import service.impl.ShowServiceImpl;
import util.Paging;

/**
 * Servlet implementation class ShowSearchController
 */
@WebServlet("/show/search")
public class ShowSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ShowService showService = new ShowServiceImpl();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String showTitle = req.getParameter("keyword");
    	int showKind = Integer.parseInt(req.getParameter("kind"));
    	
    	Paging paging = showService.getParameterPaging(req, showTitle, showKind);
    	
    	List<XShow> searchShowList = showService.getSearchShowList(req, paging);
    	
    	req.setAttribute("keyword", showTitle);
    	req.setAttribute("showList", searchShowList);
		req.setAttribute("paging", paging);
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("linkUrl", "/show/search?kind=" + req.getParameter("kind") + "&keyword=" + req.getParameter("keyword"));
		
		req.getRequestDispatcher("/WEB-INF/views/mem/show/search.jsp").forward(req, resp);

    }

}
