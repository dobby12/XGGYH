package service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.XMem;
import service.face.MemberService;

//로그인, ID/PW찾기, 회원가입
public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public XMem getLoginMem(HttpServletRequest req) {
		System.out.println("###TEST###MemberServiceImpl getLoginMem()");
//		try {
//			req.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		XMem member = new XMem();
		member.setMem_id(req.getParameter("memid"));
		member.setMem_pw(req.getParameter("mempw"));
		return member;
	}

	@Override
	public boolean loginMem(XMem mem) {
		System.out.println("###TEST###MemberServiceImpl loginMem()");
		if(memberDao.selectCntMemByMemidMempw(JDBCTemplate.getConnection(), mem)==1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public XMem getMem(XMem mem) {
		System.out.println("###TEST###MemberServiceImpl getMem()");
		return memberDao.selectMemByMemid(JDBCTemplate.getConnection(), mem);
	}

	@Override
	public XMem getJoinMember(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		XMem member = new XMem();
		
		member.setMem_id(req.getParameter("memid"));
		member.setMem_pw(req.getParameter("mempw"));
		member.setMem_nick(req.getParameter("memnick"));
		member.setMem_mail(req.getParameter("memmail"));
		member.setMail_state(req.getParameter("memstate"));
		member.setGenre_no(Integer.parseInt(req.getParameter("genreno")));
//		member.setMem_date(Integer.parseInt(req.getParameter("memdate")));
		
		System.out.println("###TEST### 멤버서비스임플 getJoinMember()의 member : " + member);
		return member;
		
	}

	@Override
	public void join(XMem member) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( memberDao.insert(conn, member) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
}
