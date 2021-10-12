package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminAskDao;
import dao.impl.AdminAskDaoImpl;
import dto.XAsk;
import dto.XComment;
import service.face.AdminAskService;
import util.Paging;

public class AdminAskServiceImpl implements AdminAskService {

	AdminAskDao adminAskDao = new AdminAskDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null입니다.");
		}
		
		int totalCount = adminAskDao.selectCntAll(JDBCTemplate.getConnection());
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public List<XAsk> getAskList(Paging paging) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<XAsk> list = adminAskDao.selectAskAll(conn, paging);
		
		return list;
	}

	@Override
	public XAsk getAskNo(HttpServletRequest req) {
		
		XAsk ask_no = new XAsk();
		
		//ask_no 전달 파라미터 검증 - !null, !""
		String param = req.getParameter("askNo");
		
		if(param!=null && !"".equals(param)) {
			ask_no.setAskNo( Integer.parseInt(param) );
		}
		
		return ask_no;
	}

	@Override
	public XAsk getAskDetail(XAsk xaskno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		XAsk xask = adminAskDao.selectAskByAskNo(conn, xaskno);
		
		return xask;
	}

	@Override
	public String getNick(XAsk xask) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return adminAskDao.getNickByMemId( conn, xask );
	}

	@Override
	public XComment setCommentWrite(HttpServletRequest req) {
		
		XComment comment = new XComment();
		
		comment.setAskNo(Integer.parseInt(req.getParameter("askNo")));
		comment.setCommentContent(req.getParameter("comment"));
		
		comment.setAdminId( req.getParameter("adminId") );
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( adminAskDao.insertComment(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.commit(conn);
		}
		
		return comment;
	}

	@Override
	public XComment getComment(XAsk xaskno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		XComment comment= adminAskDao.selectCommentByAskNo(conn, xaskno);
		
		return comment;
		
	}

	@Override
	public int getAskNoInt(HttpServletRequest req) {
		int ask_no = 0;
		
		//ask_no 전달 파라미터 검증 - !null, !""
		String param = req.getParameter("askNo");
		
		if(param!=null && !"".equals(param)) {
			 ask_no = Integer.parseInt(param);
		}
		
		return ask_no;
	}

	@Override
	public void deleteComment(XAsk xaskno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//댓글 삭제 
		adminAskDao.deleteCommentByAskNo(conn, xaskno);
		
		//댓글 상태 변환
		int res = adminAskDao.updateAskStateToN(conn, xaskno);
		
		JDBCTemplate.commit(conn);						

	}

	@Override
	public void updateAskStatetoN(XAsk xask) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = adminAskDao.updateAskStateToN(conn, xask);
		
		if( res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);			
		}
		
		
	}

	@Override
	public void updateAskStatetoY(XAsk xask) {
		Connection conn = JDBCTemplate.getConnection();
		
		int res = adminAskDao.updateAskStateToY(conn, xask);
		
		if( res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);			
		}
	}

}
