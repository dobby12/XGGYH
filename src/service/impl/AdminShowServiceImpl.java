package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminShowDao;
import dao.impl.AdminShowDaoImpl;
<<<<<<< HEAD
import dto.XFile;
=======
import dto.XAsk;
>>>>>>> 798766a036f2eb4433e3b36870f4c615d8b3812c
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
	
	@Override
	public XShow getShowno(HttpServletRequest req) {
		
		XShow showno = new XShow();
		
		String param = req.getParameter("showno");
		if(param != null && !"".equals(param)) {

			showno.setShowNo((Integer.parseInt(param)));
		}
		
		return showno;
	}
	
	@Override
	public XShow getShowDetail(XShow viewShow) {
		
		return adminShowDao.selectShowByShowno(JDBCTemplate.getConnection(), viewShow);
	}
	
	@Override
	public String getKind(XShow viewShow) {
		
		return adminShowDao.selectKindByKindno(JDBCTemplate.getConnection(), viewShow);
	}

	@Override
	public String getGenre(XShow viewShow) {

		return adminShowDao.selectGenrebyGenreno(JDBCTemplate.getConnection(), viewShow);
	}
	
	@Override
	public String getHallname(XShow viewShow) {

		return adminShowDao.selectHallnameByHallno(JDBCTemplate.getConnection(), viewShow);
	}

	@Override
	public XFile getFile(XShow viewShow) {
	
		return adminShowDao.selectFile(JDBCTemplate.getConnection(), viewShow);
	}
	
	
}
