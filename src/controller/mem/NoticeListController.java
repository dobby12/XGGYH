package controller.mem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XNotice;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;
import util.Paging;

@WebServlet("/notice")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//NoticeService 객체 생성
	private NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice [GET]");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = noticeService.getPaging(req);
		
		//게시글 전체 조회
		List<XNotice> noticeList = noticeService.getNoticeList(paging);
		
		List<String> adminNameList = new ArrayList<>();
		
		for(XNotice notice:noticeList) {
			adminNameList.add(noticeService.getAdminName(notice));
		}
		
		//조회결과 MODEL값 전달
		req.setAttribute("noticeList", noticeList);
		
		//관리자 이름 리스트 전달
		req.setAttribute("adminNameList", adminNameList);
		
		//페이징
		req.setAttribute("paging", paging);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("linkUrl", "/notice");
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/notice/list.jsp").forward(req, resp);
		
		}
}
