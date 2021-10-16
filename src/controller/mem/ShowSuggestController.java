package controller.mem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XMem;
import dto.XShow;
import service.face.MemberService;
import service.face.ShowService;
import service.impl.MemberServiceImpl;
import service.impl.ShowServiceImpl;
import util.Paging;

/**
 * Servlet implementation class ShowSuggestController
 */
@WebServlet("/show/memgenre")
public class ShowSuggestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	private ShowService showService = new ShowServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		XMem mem = null;
		XMem memInfo = null;
		int genreNo = 0;
		int kindNo = Integer.parseInt(req.getParameter("kind"));
		
		mem = memberService.getLoginMem(req);
		
		if(mem != null)
		{
			memInfo = memberService.getMem(mem);
			
			if(memInfo != null) {
				genreNo = memInfo.getGenreNo();
				
				System.out.println(genreNo);
			}
			else
			{
				System.out.println("로그인 정보를 가져오는데 실패했습니다.");
				return;
			}
		}
		else {
			System.out.println("로그인 실패");
		}
		
		Paging paging = showService.getParameterPaging(req, genreNo);
		
		List<XShow> showList = showService.getShowMemGenreList(memInfo, kindNo, paging);
		
		//XShow 테이블의 전체 정보를 가진 showList 객체를 "showList"라는 이름을 가진 요소로 설정
		req.setAttribute("showList", showList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/WEB-INF/views/mem/show/suggest.jsp").forward(req, resp);
	}
}
