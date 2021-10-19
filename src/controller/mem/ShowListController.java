package controller.mem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
 * Servlet implementation class ShowListController
 */
@WebServlet("/show")
public class ShowListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//ShowListController에서만 사용할 ShowService 객체
	private ShowService showService = new ShowServiceImpl();
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/show/list : Get");
		
		//페이징 객체 생성
		Paging paging = null;
		
		int kindNo = showService.getKindNo(req);
		
		System.out.println("showlistcontroller kindno : " + kindNo); //값 있음
		
		String kindName = showService.getkindName(kindNo);
		
		List<XShow> showList = null;
		
		if(kindNo==1) {
			req.setAttribute("imgKind", "mu");
		} else if(kindNo==2) {
			req.setAttribute("imgKind", "ac");
		} else if(kindNo==3) {
			req.setAttribute("imgKind", "co");
		} else if(kindNo==4) {
			req.setAttribute("imgKind", "op");
		}
		
		if(kindNo == 0)
		{
			//showService에서 XShow 테이블의 정보를 가진 리스트를 받아옴
			paging = showService.getPaging(req);
			showList = showService.getShowList(paging);
			List<String> welcomeMsg = new ArrayList<>();
			welcomeMsg.add("공연의 모든 것");
			welcomeMsg.add("이번 주는 이런 공연 어떠세요");
			welcomeMsg.add("좋은 사람과 좋은 공연");
			welcomeMsg.add("굿모닝, 굿애프터눈, 굿나잇");
			welcomeMsg.add("쉬어가는 날");
			welcomeMsg.add("금강산도 식후공연");
			welcomeMsg.add("봄, 여름, 가을, 겨울");
			Collections.shuffle(welcomeMsg);
			req.setAttribute("kindName", welcomeMsg.get(1)+", 공공연히");
		}
		else
		{
			//showService에서 XShow 테이블의 정보를 가진 리스트를 받아옴. 이거는 공연 종류로 가져옴
			paging = showService.getParameterPaging(req, kindNo);
			showList = showService.getShowList(paging, kindNo);
			req.setAttribute("kindName", kindName);
		}
		
		String loginId = (String)req.getSession().getAttribute("memid");
		
		if(loginId!=null) {
		
			System.out.println("로그인 되어 있는 id : " + loginId);
			
			XMem mem = new XMem();
			
			mem.setMemId(loginId);
					
			int loginIdGenreno = memberService.getMem(mem).getGenreNo();
			
			System.out.println("로그인 되어 있는 id가 선택한 genreno : " + loginIdGenreno);
			
			req.setAttribute("loginIdGenreno", loginIdGenreno);
			
			List<XShow> fiveShowList = showService.getShowGenrenoList(loginIdGenreno, kindNo);
			
			Collections.shuffle(fiveShowList);

			if(fiveShowList.size()>5) {
				List<XShow> fiveShowListCut = new ArrayList<>();				
				fiveShowListCut.add(fiveShowList.get(1));
				fiveShowListCut.add(fiveShowList.get(2));
				fiveShowListCut.add(fiveShowList.get(3));
				fiveShowListCut.add(fiveShowList.get(4));
				fiveShowListCut.add(fiveShowList.get(5));
				req.setAttribute("fiveShowList", fiveShowListCut);								
			} else {
				req.setAttribute("fiveShowList", fiveShowList);				
			}
			
			
			
		}
		//XShow 테이블의 전체 정보를 가진 showList 객체를 "showList"라는 이름을 가진 요소로 설정
		req.setAttribute("showList", showList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//종류 정보 MODEL값 전달
		req.setAttribute("kindNoToSearch", kindNo);
		
		req.setAttribute("userNick", req.getSession().getAttribute("memnick"));
		
		// /show/list 라는 url을 "linkUrl" 이라는 이름을 가진 요소로 설정 (페이징을 위해 넣은 객체)
		req.setAttribute("linkUrl", "/show?kindNo=" + kindNo);
		
		
		//요청 보내기
		req.getRequestDispatcher("/WEB-INF/views/mem/show/list.jsp").forward(req, resp);
	}
}