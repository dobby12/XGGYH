package controller.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.XMem;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### LoginController doGet()");
		req.getRequestDispatcher("/WEB-INF/views/mem/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("###TEST### LoginController doPost()"); 
		
		String kakaoemail = req.getParameter("kakaoemail");
		HttpSession session = req.getSession();

		//일반 로그인 != 카카오 로그인
		if("".equals(kakaoemail)) {
			XMem mem = memberService.getLoginMem(req);
			System.out.println("@@@1-1일반 로그인 시도");
			
			if(memberService.loginMem(mem)) {
				System.out.println("@@@1-2 성공");
				session.setAttribute("login", true);
				session.setAttribute("memid", memberService.getMem(mem).getMemId());
				session.setAttribute("memnick", memberService.getMem(mem).getMemNick());
				System.out.println("로그인 후 돌아갈 페이지 : "+req.getParameter("ref"));
				if(req.getParameter("ref")==null || "".equals(req.getParameter("ref"))) {
					resp.sendRedirect("/");					
				} else {
					resp.sendRedirect(req.getParameter("ref"));					
				}
			} else {
				System.out.println("@@@1-3 실패");
				session.setAttribute("loginfail", true);
				req.getRequestDispatcher("/WEB-INF/views/mem/login.jsp").forward(req, resp);
			}

		//카카오로 로그인일 때
		} else {
			System.out.println("@@@2-1카카오 로그인 시도");

			//카카오로 로그인한 이메일에 해당하는 회원이 소셜로그인 동의 
			if(memberService.loginMemByKakao(kakaoemail) && memberService.getKakaoAgree(kakaoemail)) {
				System.out.println("@@@2-2 성공");
				session.setAttribute("login", true);
				session.setAttribute("memid", memberService.getMemByKakao(kakaoemail).getMemId());
				session.setAttribute("memnick", memberService.getMemByKakao(kakaoemail).getMemNick());
				System.out.println("로그인 후 돌아갈 페이지 : "+req.getParameter("ref"));
				resp.sendRedirect(req.getParameter("ref"));
				
			//카카오로 로그인한 이메일에 해당하는 회원은 있으나 소셜로그인에 동의하지 않은 경우
			} else if(memberService.loginMemByKakao(kakaoemail) && !memberService.getKakaoAgree(kakaoemail)) {
				System.out.println("@@@2-3 가입자 + 소셜 미동의");
				session.setAttribute("socialagree", true);	//소셜 로그인에 동의하시겠습니까?
				session.setAttribute("kakao", kakaoemail);
				System.out.println(kakaoemail);
				req.getRequestDispatcher("/WEB-INF/views/mem/kakao.jsp").forward(req, resp);

			//카카오로 로그인한 이메일에 해당하는 회원이 없을 경우 == 미가입자
			} else if(!memberService.loginMemByKakao(kakaoemail)) {
				System.out.println("@@@2-4 미가입자 == fail");
				session.setAttribute("socialjoin", true);	//소셜 로그인에 동의하며 회원가입하시겠습니까?
				session.setAttribute("kakao", kakaoemail);
				System.out.println(kakaoemail);
				req.getRequestDispatcher("/WEB-INF/views/mem/kakao.jsp").forward(req, resp);
			
			//카카오로 로그인을 시도하였으나 실패할 경우 (카카오 로그인 실패는 jsp에서 모두 걸러지지만 혹시 모르니)
			} else {
				System.out.println("@@@2-5 실패");
				session.setAttribute("loginfail", true);
				req.getRequestDispatcher("/WEB-INF/views/mem/login.jsp").forward(req, resp);
			}
			
		}
		
	}
	
}
