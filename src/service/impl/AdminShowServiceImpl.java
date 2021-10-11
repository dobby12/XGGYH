package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminShowDao;
import dao.impl.AdminShowDaoImpl;
import dto.XAsk;
import dto.XShow;
import service.face.AdminShowService;
import util.Paging;

public class AdminShowServiceImpl implements AdminShowService {

	AdminShowDao adminShowDao = new AdminShowDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null입니다.");
		}
		
		int totalCount = adminShowDao.selectCntAll(JDBCTemplate.getConnection());
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public List<XShow> getShowList(Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<XShow> list = adminShowDao.selectShowAll(conn, paging);
		
		return list;
	}

}
