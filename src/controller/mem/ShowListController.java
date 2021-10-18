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
 * Servlet implementation class ShowListController
 */
@WebServlet("/show")
public class ShowListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//ShowListController에서만 사용할 ShowService 객체
	private ShowService showService = new ShowServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/show/list : Get");
		
		//페이징 객체 생성
		Paging paging = null;
		
		int kindNo = showService.getKindNo(req);
		
		String kindName = showService.getkindName(kindNo);
		
		List<XShow> showList = null;
		
		if(kindNo == 0)
		{
			//showService에서 XShow 테이블의 정보를 가진 리스트를 받아옴
			paging = showService.getPaging(req);
			showList = showService.getShowList(paging);
			req.setAttribute("kindName", "전체 페이지");
		}
		else
		{
			//showService에서 XShow 테이블의 정보를 가진 리스트를 받아옴. 이거는 공연 종류로 가져옴
			paging = showService.getParameterPaging(req, kindNo);
			showList = showService.getShowList(paging, kindNo);
			req.setAttribute("kindName", kindName);
		}
		
		//XShow 테이블의 전체 정보를 가진 showList 객체를 "showList"라는 이름을 가진 요소로 설정
		req.setAttribute("showList", showList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//종류 정보 MODEL값 전달
		req.setAttribute("kindNo", kindNo);
		
		if(kindNo==1) {
			req.setAttribute("imgKind", "mu");
		} else if(kindNo==2) {
			req.setAttribute("imgKind", "ac");
		} else if(kindNo==3) {
			req.setAttribute("imgKind", "co");
		} else if(kindNo==4) {
			req.setAttribute("imgKind", "op");
		}
		
		// /show/list 라는 url을 "linkUrl" 이라는 이름을 가진 요소로 설정 (페이징을 위해 넣은 객체)
		req.setAttribute("linkUrl", "/show?kindNo=" + kindNo);
		
		//요청 보내기
		req.getRequestDispatcher("/WEB-INF/views/mem/show/list.jsp").forward(req, resp);
	}
}