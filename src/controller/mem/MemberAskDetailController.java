package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XAsk;
import dto.XComment;
import service.face.AskService;
import service.impl.AskServiceImpl;

@WebServlet("/mypage/myask/detail")
public class MemberAskDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AskService askService = new AskServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/myask/detail");

		//전달파라미터 얻기 - askno
		XAsk askNo = askService.getAskNo(req);

		//상세보기 결과 조회
		XAsk detailAsk = askService.detail(askNo);
		System.out.println(detailAsk);
		
		XComment AskComment = askService.getComment(askNo);
		System.out.println(AskComment);
		
		//조회결과 MODEL값 전달
		req.setAttribute("detailAsk", detailAsk);
		req.setAttribute("AskComment", AskComment);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/mem/mypage/ask/detail.jsp").forward(req, resp);
		
	}
}
