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
    	System.out.println("/show/search [GET]");
    	
    	String showTitle = req.getParameter("keyword").trim();
    	int showKind = Integer.parseInt(req.getParameter("kindNo").trim());
    	
    	System.out.println(Integer.parseInt(req.getParameter("kindNo").trim()));
    	System.out.println("showKind : " + showKind);
    	System.out.println("showTitle : " + showTitle);
    	
    	Paging paging = null;
    	List<XShow> searchShowList =null;
    	
    	if(showKind != 0)
    	{
    		paging = showService.getParameterPaging(req, showTitle, showKind);
        	searchShowList = showService.getSearchShowList(req, paging);
    	}
    	else
    	{
    		paging = showService.getParameterPaging(req, showTitle);
    		searchShowList = showService.getSearchShowList(req, paging);
    	}
    	
    	req.setAttribute("keyword", showTitle);
    	req.setAttribute("showList", searchShowList);
    	req.setAttribute("paging", paging);
    	
    	for(XShow x : searchShowList)
    		System.out.println("x");
    	
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("linkUrl", "/show/search?kindNo=" + showKind + "&keyword=" + req.getParameter("keyword"));
		
		req.getRequestDispatcher("/WEB-INF/views/mem/show/search.jsp").forward(req, resp);

    }
}
