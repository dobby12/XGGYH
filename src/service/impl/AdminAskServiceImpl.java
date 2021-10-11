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
	public XAsk getAsk_no(HttpServletRequest req) {
		
		XAsk ask_no = new XAsk();
		
		//ask_no 전달 파라미터 검증 - !null, !""
		String param = req.getParameter("ask_no");
		
		if(param!=null && !"".equals(param)) {
			ask_no.setAsk_no( Integer.parseInt(param) );
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
		
		return adminAskDao.getNickByMem_id( conn, xask );
	}

	@Override
	public XComment setCommentWrite(HttpServletRequest req) {
		
		XComment comment = new XComment();
		
		comment.setAsk_no(Integer.parseInt(req.getParameter("ask_no")));
		comment.setComment_content(req.getParameter("comment"));
		
		comment.setAdmin_id( req.getParameter("admin_id") );
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( adminAskDao.insertComment(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.commit(conn);
		}
		
		return comment;
	}

}
