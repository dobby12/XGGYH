package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XFile;
import dto.XNotice;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - noticeno
		XNotice noticeNo = noticeService.getNoticeNo(req);

		//상세보기 결과 조회
		XNotice viewNotice = noticeService.view(noticeNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewNotice", viewNotice);

		//첨부파일 정보 조회
		XFile noticeFile = noticeService.viewFile(viewNotice);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("noticeFile", noticeFile);
		
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/notice/detail.jsp").forward(req, resp);
		
	}
}
