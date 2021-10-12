package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminMemberDao;
import dao.impl.AdminMemberDaoImpl;
import dto.XMem;
import service.face.AdminMemberService;
import util.Paging;

public class AdminMemberServiceImpl implements AdminMemberService {

	private AdminMemberDao adminMemberDao = new AdminMemberDaoImpl();

	@Override
	public Paging getPaging(HttpServletRequest req) {

		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}

		int totalCount = adminMemberDao.selectCntMemAll(JDBCTemplate.getConnection());

		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public List<XMem> getMemList(Paging paging) {

		return adminMemberDao.selectMemAll(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public XMem getMemId(HttpServletRequest req) {

		XMem memid = new XMem();

		String param = req.getParameter("memid");
		if(param != null && !"".equals(param)) {
			memid.setMemId(param);
		}

		return memid;
	}

	@Override
	public void setMemDelete(XMem memid) {

		Connection conn = JDBCTemplate.getConnection();

		if(adminMemberDao.deleteMem(conn, memid) > 0 ) {
			JDBCTemplate.commit(conn);
		} else { 
			JDBCTemplate.rollback(conn);
		}

	}

	@Override
	public List<XMem> searchMemList(HttpServletRequest req, Paging paging) {

		//전달 파라미터 얻기 - searchtype, keyword
		String searchtype = (String)req.getParameter("searchtype");
		String keyword = (String)req.getParameter("keyword");

		System.out.println(searchtype);
		System.out.println(keyword);
		
		if(searchtype == "memid") 
			return adminMemberDao.selectMemSearchByMemnick(JDBCTemplate.getConnection(), keyword, paging);
		 else 
			return adminMemberDao.selectMemSearchByMemid(JDBCTemplate.getConnection(), keyword, paging);
		
		
	}
}	
