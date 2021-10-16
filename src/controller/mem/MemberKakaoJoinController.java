package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.XMem;
import service.face.MemberService;
import service.impl.MemberServiceImpl;


@WebServlet("/kakaojoin")
public class MemberKakaoJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		XMem mem = new XMem();
		
		mem.setMemId(req.getParameter("memid"));
		mem.setMemPw(req.getParameter("mempw"));
		mem.setMemNick(req.getParameter("memnick"));
		mem.setMemMail(req.getParameter("memmail"));
		mem.setMailState(req.getParameter("memstate"));
		mem.setGenreNo(Integer.parseInt(req.getParameter("genreno")));
		
		try {
			memberService.setMemWithKakao(mem);			
			resp.getWriter().print(true);
		} catch(Exception e) {
			resp.getWriter().print(false);	
		}
		return;
		
	}

}
