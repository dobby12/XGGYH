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
	public boolean checkId(String memid) {
		Connection conn = JDBCTemplate.getConnection();
		
		XMem member = memberDao.selectMemByMemid(conn, memid);
		
		if (member != null) {
			// 이미 존재하는 아이디
			return true;
		} else {
			// 존재하지 않는 아이디
			return false;
		}
	}
	
	@Override
	public boolean checkEmail(String memmail) {
		Connection conn = JDBCTemplate.getConnection();
		
		String memid = memberDao.selectMemidByMemmail(conn, memmail);
		
		if (memid != null) {
			// 이미 존재하는 이메일
			return true;
		} else {
			// 존재하지 않는 이메일
			return false;
		}
	}

	@Override
	public String getMemid(String parameter) {
		return memberDao.selectMemidByMemmail(JDBCTemplate.getConnection(), parameter);
	}

	@Override
	public void setMempwUpdate(String mailForPw, String uuidPw) {
		Connection conn = JDBCTemplate.getConnection();
		if(memberDao.updateMempw(conn, mailForPw, uuidPw) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public XMem getMyInfo(String memid) {
		return memberDao.selectMemByMemid(JDBCTemplate.getConnection(), memid);
	}

	@Override
	public XMem getUpdate(String memid) {
		return memberDao.selectMemByMemid(JDBCTemplate.getConnection(), memid);
	}

	@Override
	public int updateMem(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Connection conn = JDBCTemplate.getConnection();
		
		XMem mem = new XMem();
		
		mem.setMemId(req.getParameter("memid"));
		if (req.getParameter("mempw").length() > 0) {
			mem.setMemPw(req.getParameter("mempw"));
		} else {
			mem.setMemPw(memberDao.selectMemByMemid(conn, mem.getMemId()).getMemPw());
		}
	
		mem.setMemNick(req.getParameter("memnick"));
		mem.setMemMail(req.getParameter("memmail"));
		mem.setMailState(req.getParameter("mailstate"));
		mem.setGenreNo(Integer.parseInt(req.getParameter("genreno")));
		
		System.out.println("###TEST### 멤버서비스임플 getJoinMember()의 member : " + mem);
				
		if (memberDao.updateMem(conn, mem) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}	
		req.getSession().setAttribute("memnick", mem.getMemNick());
		return 1;
	}

	@Override
	public void setMemDelete(XMem mem) {
		Connection conn = JDBCTemplate.getConnection();

		if(memberDao.deleteMem(conn, mem) > 0 ) {
			JDBCTemplate.commit(conn);
		} else { 
			JDBCTemplate.rollback(conn);
		}
	}
	
	
	
	
	@Override
	public boolean loginMemByKakao(String kakaoemail) {
		if(memberDao.selectCntMemByKakao(JDBCTemplate.getConnection(), kakaoemail)==1) {
			return true;
		} else {
			return false;			
		}
	}
	
	@Override
	public XMem getMemByKakao(String kakaoemail) {
		System.out.println("@@@2 : "+kakaoemail);
		return memberDao.selectMemByKakao(JDBCTemplate.getConnection(), kakaoemail);
	}
	
	@Override
	public boolean getKakaoAgree(String kakaoemail) {
		if(memberDao.selectCntMemByKakaoY(JDBCTemplate.getConnection(), kakaoemail)==1) {
			return true;
		} else {
			return false;			
		}
	}
	
	@Override
	public void setKakaoByMemmail(String kakaoagree) {
		Connection conn = JDBCTemplate.getConnection();
		if(memberDao.updateMemKakaoByMemmail(conn, kakaoagree)==1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
	
	@Override
	public void setMemWithKakao(XMem mem) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( memberDao.insertMemWithKakao(conn, mem) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
}
