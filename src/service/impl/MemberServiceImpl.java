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
		member.setMemId(req.getParameter("memid"));
		member.setMemPw(req.getParameter("mempw"));
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
		
		XMem mem = new XMem();
		
		mem.setMemId(req.getParameter("memid"));
		mem.setMemPw(req.getParameter("mempw"));
		mem.setMemNick(req.getParameter("memnick"));
		mem.setMemMail(req.getParameter("memmail"));
		mem.setMailState(req.getParameter("memstate"));
		mem.setGenreNo(Integer.parseInt(req.getParameter("genreno")));
		
		System.out.println("###TEST### 멤버서비스임플 getJoinMember()의 member : " + mem);
		return mem;
		
	}

	@Override
	public void join(XMem mem) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( memberDao.insert(conn, mem) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public String getMemid(String parameter) {
		return memberDao.selectMemidByMemmail(JDBCTemplate.getConnection(), parameter);
	}
}
