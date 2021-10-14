package controller.mem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XMem;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/mypage/myinfo")
public class MemberDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberservice = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인한 회원아이디
		String memid = (String)req.getSession().getAttribute("memid");
		
		List<XMem> memidMyinfoList = memberService.getId
		
	}
}
