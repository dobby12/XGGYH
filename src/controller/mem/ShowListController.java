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

/**
 * Servlet implementation class ShowListController
 */
@WebServlet("/show/list")
public class ShowListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//ShowListController에서만 사용할 ShowService 객체
	private ShowService showService = new ShowServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/show/list : Get");
		
		//showService에서 XShow 테이블의 정보를 가진 리스트를 받아옴
		List<XShow> showList = showService.getShowList();
		
		//XShow 테이블의 전체 정보를 가진 showList 객체를 "showList"라는 이름을 가진 요소로 설정
		req.setAttribute("showList", showList);
		
		// /show/list 라는 url을 "linkUrl" 이라는 이름을 가진 요소로 설정
		req.setAttribute("linkUrl", "/show/list");
		
		//요청 보내기
		req.getRequestDispatcher("/WEB-INF/views/mem/show/list.jsp").forward(req, resp);
	}
}