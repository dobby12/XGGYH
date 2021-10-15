package controller.mem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/kakao")
public class MemberKakaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//가입자 + 소셜 미동의
		if(req.getParameter("kakaoagree")!=null) {
			String kakaoagree = req.getParameter("kakaoagree"); 
			System.out.println("소셜 미동의한 일반 가입자 이메일 : " + kakaoagree);
			memberService.setKakaoByMemmail(kakaoagree);
			System.out.println("로그인 후 돌아갈 페이지 : "+req.getParameter("ref"));
			resp.sendRedirect(req.getParameter("ref"));
		//미가입자
		} else if(req.getParameter("kakaojoin")!=null) {
			String kakaojoin = req.getParameter("kakaojoin");
			System.out.println("소셜 로그인한 미가입자 이메일 : " + kakaojoin);
		}
		
	}
}
