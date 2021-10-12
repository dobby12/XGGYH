package controller.mem;

import java.io.IOException;
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
		
		//조회결과 MODEL값 전달
		req.setAttribute("noticeList", noticeList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("linkUrl", "/mem/mypage/notice");
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/notice.jsp").forward(req, resp);
		
		}
}
